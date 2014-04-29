/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usermodule.service;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.ejb.Remote;
import usermodule.entity.ITSManager;

/**
 *
 * @author vincze.attila
 */
@Remote
public interface UserServiceRemote {

//    long findIdByLoginName(String loginName);
//
//    boolean canExecute(String serviceName);
//
//    List<UserDTO> getUserList() throws TooManyResultsException, ZeroResultException;
//
//    List<UserDTO> getUserList(String name, String userType) throws TooManyResultsException, ZeroResultException;
//
//    List<SessionDTO> getSessionListByUser(long userId);
//
//    List<SessionDTO> getSessionList();
//
//    List<SessionDTO> getSessionList(String userName, Date date);
//
//    void activate(long userId, boolean active);
//
//    List<TDSManagerDTO> getTDSManagerList() throws TooManyResultsException, ZeroResultException;
//
//    List<TDSManagerDTO> getTDSManagerList(String name) throws TooManyResultsException, ZeroResultException;
//
//    TDSManagerDTO saveTDSManager(TDSManagerDTO dto);
//
//    TDSManagerDTO getTDSManager(long id);
//
//    TDSManagerRoleAssignmentDTO saveTDSManagerRoleAssignment(TDSManagerRoleAssignmentDTO dto);
//
//    List<HospitalStaffDTO> getHospitalStaffList() throws TooManyResultsException, ZeroResultException;
//
//    List<HospitalStaffDTO> getHospitalStaffList(String name) throws TooManyResultsException, ZeroResultException;
//
//    HospitalStaffDTO getHospitalStaff(long id);
//
//    HospitalStaffDTO saveHospitalStaff(HospitalStaffDTO dto);
//
//    List<TDSRadiologistDTO> getTDSRadiologistList() throws TooManyResultsException, ZeroResultException;
//
//    List<TDSRadiologistDTO> getTDSRadiologistList(String name) throws TooManyResultsException, ZeroResultException;
//
//    TDSRadiologistDTO getTDSRadiologist(long id);
//
//    TDSRadiologistDTO saveTDSRadiologist(TDSRadiologistDTO dto);

    byte[] getITSManagerList(byte[] input) throws TooManyResultsException, ZeroResultException;

    byte[] getITSManager(byte[] input);

    byte[] saveITSManager(byte[] input);
    
}
