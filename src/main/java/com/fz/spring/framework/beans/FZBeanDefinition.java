package com.fz.spring.framework.beans;

public class FZBeanDefinition {
    String beanClassName;
    String factoryBeanName;
    Object beanClass;
    public FZBeanDefinition(String beanName, String factoryBeanName) {
        this.beanClassName = beanName;
        this.factoryBeanName = factoryBeanName;
        try {
            Class<?> clazz = Class.forName(beanName);
            this.beanClass = clazz;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public Object getBeanClass() {
        return beanClass;
    }
}
