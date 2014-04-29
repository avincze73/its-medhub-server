/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package systemmodule.entity;

import casemodule.entity.TDSCase;
import usermodule.entity.ITSUser;
import radiologistmodule.entity.ITSRadiologist;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vincze.attila
 */
@Entity
@Table(name = "SystemMessage")
@NamedQueries({
    @NamedQuery(name = "SystemMessage.findAll", query = "SELECT s FROM SystemMessage s"),
    @NamedQuery(name = "SystemMessage.findById", query = "SELECT s FROM SystemMessage s WHERE s.id = :id"),
    @NamedQuery(name = "SystemMessage.findByEmailWasSent", query = "SELECT s FROM SystemMessage s WHERE s.emailWasSent = :emailWasSent"),
    @NamedQuery(name = "SystemMessage.findByEmailAddress", query = "SELECT s FROM SystemMessage s WHERE s.emailAddress = :emailAddress"),
    @NamedQuery(name = "SystemMessage.findByMessageText", query = "SELECT s FROM SystemMessage s WHERE s.messageText = :messageText"),
    @NamedQuery(name = "SystemMessage.findBySent", query = "SELECT s FROM SystemMessage s WHERE s.sent = :sent")})
public class SystemMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "emailWasSent")
    private boolean emailWasSent;
    @Column(name = "emailAddress")
    private String emailAddress;
    @Basic(optional = false)
    @Column(name = "messageText")
    private String messageText;
    @Basic(optional = false)
    @Column(name = "sent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sent;
    @JoinColumn(name = "caseId", referencedColumnName = "id")
    @ManyToOne
    private TDSCase tDSCase;
    @JoinColumn(name = "tdsRadiologistId", referencedColumnName = "id")
    @ManyToOne
    private ITSRadiologist tDSRadiologist;
    @JoinColumn(name = "systemMessageTypeId", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private SystemMessageType systemMessageType;
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @ManyToOne
    private ITSUser tDSUser;

    public SystemMessage() {
    }

    public SystemMessage(Long id) {
        this.id = id;
    }

    public SystemMessage(Long id, boolean emailWasSent, String messageText, Date sent) {
        this.id = id;
        this.emailWasSent = emailWasSent;
        this.messageText = messageText;
        this.sent = sent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getEmailWasSent() {
        return emailWasSent;
    }

    public void setEmailWasSent(boolean emailWasSent) {
        this.emailWasSent = emailWasSent;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public TDSCase getTDSCase() {
        return tDSCase;
    }

    public void setTDSCase(TDSCase tDSCase) {
        this.tDSCase = tDSCase;
    }

    public ITSRadiologist getTDSRadiologist() {
        return tDSRadiologist;
    }

    public void setTDSRadiologist(ITSRadiologist tDSRadiologist) {
        this.tDSRadiologist = tDSRadiologist;
    }

    public SystemMessageType getSystemMessageType() {
        return systemMessageType;
    }

    public void setSystemMessageType(SystemMessageType systemMessageType) {
        this.systemMessageType = systemMessageType;
    }

    public ITSUser getTDSUser() {
        return tDSUser;
    }

    public void setTDSUser(ITSUser tDSUser) {
        this.tDSUser = tDSUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemMessage)) {
            return false;
        }
        SystemMessage other = (SystemMessage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SystemMessage[id=" + id + "]";
    }

}
