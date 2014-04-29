/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package casemodule.service;

import casemodule.assembler.CaseAssembler;
import casemodule.dto.SystemMessageDTO;
import casemodule.dto.SystemMessageTypeDTO;
import casemodule.entity.TDSCase;
import casemodule.repository.SystemMessageRepository;
import casemodule.repository.SystemMessageTypeRepository;
import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import radiologistmodule.entity.ITSRadiologist;
import systemmodule.entity.SystemMessage;
import systemmodule.entity.SystemMessageType;
import usermodule.entity.ITSUser;
import usermodule.repository.TDSUserRepository;

/**
 *
 * @author vincze.attila
 */
@LocalBean
@Stateless
public class SystemMessageService implements SystemMessageServiceRemote {
    @EJB(name = "userRepository")
    private TDSUserRepository userRepository;

    //@Resource(name = "mail/hssmed")
    //private Session mailhssmed;
    @EJB(name = "systemMessageTypeRepository")
    private SystemMessageTypeRepository systemMessageTypeRepository;
    @EJB(name = "repository")
    private SystemMessageRepository repository;


    @Override
    public List<SystemMessageDTO> getSystemMessageList(long userId, long systemMessageTypeId)
            throws TooManyResultsException, ZeroResultException {
        List<SystemMessageDTO> dtos = new ArrayList<SystemMessageDTO>();
        List<SystemMessage> entities = repository.findByUserAndType(userId, systemMessageTypeId);
        for (SystemMessage systemMessage : entities) {
            dtos.add(CaseAssembler.toDTO(systemMessage));
        }
        return dtos;
    }

    @Override
    public List<SystemMessageTypeDTO> getSystemMessageTypeList()
            throws TooManyResultsException, ZeroResultException {
        List<SystemMessageTypeDTO> dtos = new ArrayList<SystemMessageTypeDTO>();
        List<SystemMessageType> entities = systemMessageTypeRepository.findAll();
        for (SystemMessageType systemMessageType : entities) {
            dtos.add(CaseAssembler.toDTO(systemMessageType));
        }
        return dtos;
    }

    private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
//        MimeMessage message = new MimeMessage(mailhssmed);
//        message.setSubject(subject);
//        message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
//        message.setText(body);
//        Transport.send(message);
    }

    @Override
    public void save(long userId, long caseId, long radiologistId, String messageText) {
        SystemMessage entity = new SystemMessage();
        entity.setTDSUser(new ITSUser(userId));
        entity.setTDSCase(new TDSCase(caseId));
        if (radiologistId != 0) {
            entity.setTDSRadiologist(new ITSRadiologist(radiologistId));
        }
        entity.setMessageText(messageText);
        entity.setSystemMessageType(new SystemMessageType((long)1));
        entity.setSent(new Date());
        repository.create(entity);
        ITSUser user = userRepository.find(new Long(userId));
        try {
            sendMail(user.getItsEmail(), "subject", messageText);
            entity.setEmailAddress(user.getItsEmail());
            entity.setEmailWasSent(true);
            repository.edit(entity);
        } catch (NamingException ex) {
            Logger.getLogger(SystemMessageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SystemMessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
