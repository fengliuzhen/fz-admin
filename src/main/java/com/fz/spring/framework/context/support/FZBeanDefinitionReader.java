package com.fz.spring.framework.context.support;

import com.fz.spring.framework.beans.FZBeanDefinition;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

////对配置文件进行解析, 涉及到对Bean的定位,加载,注册,注入等
public class FZBeanDefinitionReader {
    private final String SCAN_Package = "";
    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    private List<String> registedBeanDefinitionsClassName = new ArrayList<>();



    public FZBeanDefinitionReader(String... locations) {

        //定位
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));

        //加载
        try {
            properties.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //注册

        String strPackage = properties.get("scanPackage").toString();

        //记录下配置文件中Bean包下所有的类
        doScannerBeanClassnames(strPackage);

    }

    public static String lowerFristCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public FZBeanDefinition doRegister(String beanClassName) throws  Exception {

        if (this.registedBeanDefinitionsClassName.contains(beanClassName)) {
            FZBeanDefinition beanDefinition = new FZBeanDefinition(beanClassName, lowerFristCase(beanClassName.substring(beanClassName.lastIndexOf(".") + 1)));
            return beanDefinition;
        }
        return null;
    }

    //扫描指定包下所有的类
    public void doScannerBeanClassnames(String packageName) {

        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());
        for (File fileT : classDir.listFiles()) {
            if (fileT.isDirectory()) {
                doScannerBeanClassnames(packageName + "." + fileT.getName());
            } else {
                registedBeanDefinitionsClassName.add(packageName + "." + fileT.getName().replace(".class", ""));
            }
        }
    }

    public List<String> getRegistedBeanDefinitionsClassName() {
        return registedBeanDefinitionsClassName;
    }
}
