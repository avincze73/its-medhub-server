/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

/**
 *
 * @author itsadmin
 */
public class RemoteMessage implements Serializable {

    private byte[] message;

    public RemoteMessage(byte[] message) {
        this.message = message;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }
}
