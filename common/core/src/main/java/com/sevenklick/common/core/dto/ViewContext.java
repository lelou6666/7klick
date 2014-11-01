package com.sevenklick.common.core.dto;

import com.sevenklick.common.core.domain.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierre.petersson on 04/07/2014.
 */

public class ViewContext {
    private Long id;
    private String view;
    private Long tenantId;
    private List<ViewContextElement> viewContextElements=new ArrayList<ViewContextElement>();

    public ViewContext(){
        super();
    }
    public ViewContext(String viewName, Context context){
        this.view=viewName;
        this.tenantId=context.getTenantId();
    }
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public List<ViewContextElement> getViewContextElements() {
        return viewContextElements;
    }

    public void setViewContextElements(List<ViewContextElement> viewContextElements) {
        this.viewContextElements = viewContextElements;
    }
    public void addViewContextElements(ViewContextElement viewContextElements) {
        this.viewContextElements.add(viewContextElements);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
