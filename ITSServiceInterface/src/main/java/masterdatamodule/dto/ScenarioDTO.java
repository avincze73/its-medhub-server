/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package masterdatamodule.dto;

import base.BaseDTO;

/**
 *
 * @author vincze.attila
 */
public class ScenarioDTO extends BaseDTO {

    private String englishName;
    private String hungarianName;
    private boolean radiologistStarts;
    private String category;
    private int behaviour;
    private String code;
    private boolean showFlag;
    //error
    private boolean englishNameError;
    private boolean hungarianNameError;
    private boolean radiologistStartsError;
    private boolean categoryError;
    private boolean behaviourError;
    //optional
    private boolean englishNameOptional;
    private boolean hungarianNameOptional;
    private boolean radiologistStartsOptional;
    private boolean categoryOptional;
    private boolean behaviourOptional;
    //Ezek a mezők nincsennek mentve az adatbázisban.
    private String note;
    private boolean started;
    private String idBehaviourStarted;

    public ScenarioDTO(long id) {
        this(id, null, null, false, null, 0);
    }

    public ScenarioDTO() {
        this(0, null, null, false, null, 0);
    }

    public ScenarioDTO(long id, String englishName, String hungarianName,
            boolean radiologistStarts, String category, int behaviour) {
        super(id);
        this.englishName = englishName;
        this.hungarianName = hungarianName;
        this.radiologistStarts = radiologistStarts;
        this.category = category;
        this.behaviour = behaviour;
    }

    public ScenarioDTO(String englishName, String hungarianName) {
        this(0, englishName, hungarianName, false, null, 0);
    }

    public ScenarioDTO(int id, String englishName, String hungarianName) {
        this(id, englishName, hungarianName, false, null, 0);
    }

    public ScenarioDTO(String englishName, String hungarianName, boolean radiologistStarts, String category, int behaviour) {
        this(0, englishName, hungarianName, radiologistStarts, category, behaviour);
    }

    @Override
    public ScenarioDTO clone() throws CloneNotSupportedException {
        ScenarioDTO result = (ScenarioDTO) super.clone();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ScenarioDTO)) {
            return false;
        }
        return super.equals(obj);
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        String oldValue = this.englishName;
        this.englishName = englishName;
        propertyChangeSupport.firePropertyChange("englishName", oldValue, this.englishName);
    }

    public String getHungarianName() {
        return hungarianName;
    }

    public void setHungarianName(String hungarianName) {
        String oldValue = this.hungarianName;
        this.hungarianName = hungarianName;
        propertyChangeSupport.firePropertyChange("hungarianName", oldValue, this.hungarianName);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        String oldValue = this.category;
        this.category = category;
        propertyChangeSupport.firePropertyChange("category", oldValue, this.category);
    }

    public boolean isRadiologistStarts() {
        return radiologistStarts;
    }

    public void setRadiologistStarts(boolean radiologistStarts) {
        boolean oldValue = this.radiologistStarts;
        this.radiologistStarts = radiologistStarts;
        propertyChangeSupport.firePropertyChange("radiologistStarts", oldValue, this.radiologistStarts);
    }

    public int getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(int behaviour) {
        int oldValue = this.behaviour;
        this.behaviour = behaviour;
        propertyChangeSupport.firePropertyChange("behaviour", oldValue, this.behaviour);
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        String oldValue = this.note;
        this.note = note;
        propertyChangeSupport.firePropertyChange("note", oldValue, this.note);
    }

    public String getIdBehaviourStarted() {
        idBehaviourStarted = String.format("%d;%d;%b", getId(), behaviour, started);
        return idBehaviourStarted;
    }

    public void setIdBehaviourStarted(String idBehaviourStarted) {
        String oldValue = this.idBehaviourStarted;
        this.idBehaviourStarted = idBehaviourStarted;
        propertyChangeSupport.firePropertyChange("idBehaviourStarted", oldValue, this.idBehaviourStarted);
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        boolean oldValue = this.started;
        this.started = started;
        propertyChangeSupport.firePropertyChange("started", oldValue, this.started);
        setIdBehaviourStarted(String.format("%d;%d;%b", getId(), behaviour, started));
    }

    public boolean isBehaviourError() {
        return behaviourError;
    }

    public void setBehaviourError(boolean behaviourError) {
        boolean oldValue = this.behaviourError;
        this.behaviourError = behaviourError;
        propertyChangeSupport.firePropertyChange("behaviourError", oldValue, this.behaviourError);
    }

    public boolean isBehaviourOptional() {
        return behaviourOptional;
    }

    public void setBehaviourOptional(boolean behaviourOptional) {
        boolean oldValue = this.behaviourOptional;
        this.behaviourOptional = behaviourOptional;
        propertyChangeSupport.firePropertyChange("behaviourOptional", oldValue, this.behaviourOptional);
    }

    public boolean isCategoryError() {
        return categoryError;
    }

    public void setCategoryError(boolean categoryError) {
        boolean oldValue = this.categoryError;
        this.categoryError = categoryError;
        propertyChangeSupport.firePropertyChange("categoryError", oldValue, this.categoryError);
    }

    public boolean isCategoryOptional() {
        return categoryOptional;
    }

    public void setCategoryOptional(boolean categoryOptional) {
        boolean oldValue = this.categoryOptional;
        this.categoryOptional = categoryOptional;
        propertyChangeSupport.firePropertyChange("categoryOptional", oldValue, this.categoryOptional);
    }

    public boolean isEnglishNameError() {
        return englishNameError;
    }

    public void setEnglishNameError(boolean englishNameError) {
        boolean oldValue = this.englishNameError;
        this.englishNameError = englishNameError;
        propertyChangeSupport.firePropertyChange("englishNameError", oldValue, this.englishNameError);
    }

    public boolean isEnglishNameOptional() {
        return englishNameOptional;
    }

    public void setEnglishNameOptional(boolean englishNameOptional) {
        boolean oldValue = this.englishNameOptional;
        this.englishNameOptional = englishNameOptional;
        propertyChangeSupport.firePropertyChange("englishNameOptional", oldValue, this.englishNameOptional);
    }

    public boolean isHungarianNameError() {
        return hungarianNameError;
    }

    public void setHungarianNameError(boolean hungarianNameError) {
        boolean oldValue = this.hungarianNameError;
        this.hungarianNameError = hungarianNameError;
        propertyChangeSupport.firePropertyChange("hungarianNameError", oldValue, this.hungarianNameError);
    }

    public boolean isHungarianNameOptional() {
        return hungarianNameOptional;
    }

    public void setHungarianNameOptional(boolean hungarianNameOptional) {
        boolean oldValue = this.hungarianNameOptional;
        this.hungarianNameOptional = hungarianNameOptional;
        propertyChangeSupport.firePropertyChange("hungarianNameOptional", oldValue, this.hungarianNameOptional);
    }

    public boolean isRadiologistStartsError() {
        return radiologistStartsError;
    }

    public void setRadiologistStartsError(boolean radiologistStartsError) {
        boolean oldValue = this.radiologistStartsError;
        this.radiologistStartsError = radiologistStartsError;
        propertyChangeSupport.firePropertyChange("radiologistStartsError", oldValue, this.radiologistStartsError);
    }

    public boolean isRadiologistStartsOptional() {
        return radiologistStartsOptional;
    }

    public void setRadiologistStartsOptional(boolean radiologistStartsOptional) {
        boolean oldValue = this.radiologistStartsOptional;
        this.radiologistStartsOptional = radiologistStartsOptional;
        propertyChangeSupport.firePropertyChange("radiologistStartsOptional", oldValue, this.radiologistStartsOptional);
    }
}
