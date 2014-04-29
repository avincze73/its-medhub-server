/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.service;

import common.exception.AuthenticationFailedException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

/**
 *
 * @author root
 */
@Stateless
@LocalBean
public class ConnectionService implements ConnectionServiceRemote {

//    @Resource(name = "mail/hssmed")
//    private Session mailhssmed;
    @Resource
    private SessionContext ctx;

    @Override
    public void init() {
            System.out.println(ctx.getCallerPrincipal().getName());
            //sendMail("avincze@fotnet.hu","tds","This is a mail from TDS");
            //sendEmails();
    }

    private void sendEmails() throws NoSuchProviderException, MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtps");
        props.setProperty("mail.smtps.host", "smtp.gmail.com");
        //props.put("mail.smtps.auth", "true");
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        MimeMessage message = new MimeMessage(mailSession);
        message.setSubject("HTML  mail with images");
        message.setFrom(new InternetAddress("info@tds.com"));
        message.setContent("<h1>Hello from TDS</h1>", "text/html");
        message.addRecipient(Message.RecipientType.TO,
                new InternetAddress("avincze@fotnet.hu"));
        Transport transport = mailSession.getTransport("smtps");
        transport.connect("smtp.gmail.com", "tds@hss-med.eu", "diagdance");
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private void sendMail(String email, String subject, String body) throws NamingException, MessagingException {
//        MimeMessage message = new MimeMessage(mailhssmed);
//        message.setSubject(subject);
//        message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
//        message.setText(body);
//        Transport.send(message);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
