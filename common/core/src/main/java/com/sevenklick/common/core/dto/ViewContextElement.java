package com.sevenklick.common.core.dto;

/**
 * Created by pierre.petersson on 04/07/2014.
 */
public class ViewContextElement {

    private String element;
    private String type;
    private String value;
    public ViewContextElement() {
    }

    public ViewContextElement(String element, String type, String value) {
        this.element = element;
        this.type = type;
        this.value = value;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
