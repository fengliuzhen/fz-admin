package com.fz.spring.framework.webmvc;

import java.util.Map;

public class FZModelAndView {
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getDataModel() {
        return dataModel;
    }

    public void setDataModel(Map<String, ?> dataModel) {
        this.dataModel = dataModel;
    }

    private String viewName;
    private Map<String,?> dataModel;

    public FZModelAndView(String viewName, Map<String, ?> dataModel) {
        this.viewName = viewName;
        this.dataModel = dataModel;
    }
}
