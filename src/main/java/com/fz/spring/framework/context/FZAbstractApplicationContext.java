package com.fz.spring.framework.context;

public abstract class FZAbstractApplicationContext {
    protected void onRefresh(){

    }
    protected abstract void refreshBeanFactory();
}
