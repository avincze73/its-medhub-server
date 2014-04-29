/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.TooManyResultsException;
import common.exception.ZeroResultException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author root
 */
@Remote
public interface IRemoteService<T> {

    List<T> findAll() throws TooManyResultsException, ZeroResultException;

    List<T> findByName(String name) throws TooManyResultsException, ZeroResultException;

    T find(long id);

    long save(T dto);

    void delete(long id);
}
