package com.fz.spring.framework.context;

import com.fz.spring.framework.beans.FZBeanDefinition;
import com.fz.spring.framework.beans.FZBeanWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FZDefaultListableBeanFactory extends FZAbstractApplicationContext  {
    //用来存储BeanDefinition的定义
    protected Map<String,FZBeanDefinition> beanDefinitionMap= new ConcurrentHashMap<>();
    //用来存储实例化的单例Bean对象
    protected Map<String,FZBeanWrapper>  singleBeanInstanceMap= new ConcurrentHashMap<>();

    protected List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    protected void refreshBeanFactory() {

    }
}
