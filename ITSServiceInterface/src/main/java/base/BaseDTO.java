/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import common.exception.ConstraintException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtils;
import usermodule.dto.SessionLogEntryActionType;
import usermodule.dto.SessionLogEntryDTO;

/**
 *
 * @author vincze.attila
 */
public class BaseDTO implements Serializable, Cloneable {

    private long id;
    protected PropertyChangeSupport propertyChangeSupport;
    protected VetoableChangeSupport vetoableChangeSupport;

    public BaseDTO() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        vetoableChangeSupport = new VetoableChangeSupport(this);
        id = 0;
    }

    public BaseDTO(long id) {
        propertyChangeSupport = new PropertyChangeSupport(this);
        vetoableChangeSupport = new VetoableChangeSupport(this);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        long oldValue = this.id;
        this.id = id;
        propertyChangeSupport.firePropertyChange("id", oldValue, this.id);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    @Override
    public int hashCode() {
        Long intObj = new Long(id);
        return intObj.hashCode();
    }

    @Override
    public BaseDTO clone() throws CloneNotSupportedException {
        BaseDTO result = (BaseDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getId() == 0 ? false : this.getId() == ((BaseDTO) obj).getId();
    }

    public void Validate(ResourceBundle bundle) throws ConstraintException {
    }

    public void Validate() throws ConstraintException {
    }

    public void validate() throws ConstraintException {
        HashSet<String> errors = new HashSet<String>();
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                if (field.getName().contains("Optional") && !Boolean.parseBoolean(PropertyUtils.getProperty(this, field.getName()).toString())) {
                    String attr = field.getName().replace("Optional", "");
                    String err = field.getName().replace("Optional", "Error");
                    if (PropertyUtils.getProperty(this, attr) == null || "".equals(PropertyUtils.getProperty(this, attr).toString())) {
                        PropertyUtils.setProperty(this, err, true);
                        errors.add(attr);
                    }
                }
            } catch (InvocationTargetException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!errors.isEmpty()) {
            throw new ConstraintException("Az alábbi mezők kitöltése kötelező! \n" + errors.toString());
        }

    }

    private String getClassName() {
        String fullClassName = this.getClass().getName();
        int firstChar;
        firstChar = fullClassName.lastIndexOf('.') + 1;
        if (firstChar > 0) {
            fullClassName = fullClassName.substring(firstChar);
        }
        fullClassName = fullClassName.replace("DTO", "");
        return fullClassName;
    }

    public List<SessionLogEntryDTO> getModifications(BaseDTO original) {
        List<SessionLogEntryDTO> ret = new ArrayList<SessionLogEntryDTO>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getName().contains("Error") || field.getName().contains("Optional")) {
                continue;
            }
            try {
                if (original == null) {
                    SessionLogEntryDTO log = new SessionLogEntryDTO();
                    log.setActionType(SessionLogEntryActionType.add.name());
                    log.setColumnName(field.getName());
                    log.setTableName(getClassName());
                    log.setFromValue(null);
                    log.setToValue(PropertyUtils.getProperty(this, field.getName()) == null ? null : PropertyUtils.getProperty(this, field.getName()).toString());
                    if (log.getFromValue() != log.getToValue()) {
                        ret.add(log);
                    }
                } else if (PropertyUtils.getProperty(this, field.getName()) != PropertyUtils.getProperty(original, field.getName())) {
                    SessionLogEntryDTO log = new SessionLogEntryDTO();
                    log.setActionType(SessionLogEntryActionType.mod.name());
                    log.setColumnName(field.getName());
                    log.setTableName(getClassName());
                    //log.setFromValue(PropertyUtils.getProperty(original, field.getName()).toString());
                    //log.setToValue(PropertyUtils.getProperty(this, field.getName()).toString());
                    log.setFromValue(PropertyUtils.getProperty(original, field.getName()) == null ? null : PropertyUtils.getProperty(original, field.getName()).toString());
                    log.setToValue(PropertyUtils.getProperty(this, field.getName()) == null ? null : PropertyUtils.getProperty(this, field.getName()).toString());
                    ret.add(log);
                }
            } catch (IllegalAccessException ex) {
                Logger.getLogger(BaseDTO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(BaseDTO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(BaseDTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }
}
