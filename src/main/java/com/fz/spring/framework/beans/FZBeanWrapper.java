package com.fz.spring.framework.beans;

public class FZBeanWrapper {
    private Object _originalBean;
    private Object _wrapperedBean;

    public FZBeanWrapper(Object originalBean) {
        _originalBean = originalBean;
        this._wrapperedBean = originalBean;
    }

    public Object get_originalBean() {
        return _originalBean;
    }

    public void set_originalBean(Object _originalBean) {
        this._originalBean = _originalBean;
    }

    public Object get_wrapperedBean() {
        return _wrapperedBean;
    }

    public void set_wrapperedBean(Object _wrapperedBean) {
        this._wrapperedBean = _wrapperedBean;
    }
}
