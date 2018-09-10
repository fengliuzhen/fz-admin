package com.fz.spring.framework.webmvc.servlet;

import com.fz.spring.framework.annotation.FZController;
import com.fz.spring.framework.annotation.FZRequestMapping;
import com.fz.spring.framework.annotation.FZRequestParam;
import com.fz.spring.framework.beans.FZBeanWrapper;
import com.fz.spring.framework.context.FZWebApplicationContext;
import com.fz.spring.framework.webmvc.FZHandlerAdapter;
import com.fz.spring.framework.webmvc.FZHandlerMapping;
import com.fz.spring.framework.webmvc.FZModelAndView;
import com.fz.spring.framework.webmvc.FZViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FZDispatcherServlet extends HttpServlet {
    private final String LOCATION = "contextConfigLocation";
    FZWebApplicationContext applicationContext = null;

    private List<FZHandlerMapping> handlerMappings = new ArrayList<>();
    private Map<FZHandlerMapping, FZHandlerAdapter> handlerAdapterMap = new HashMap<>();
    private List<FZViewResolver> viewResolvers = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        resp.getWriter().write("this is a hello world");
        try {
            doDispatch(req, resp);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String strLocation = config.getInitParameter(LOCATION);
        applicationContext = new FZWebApplicationContext(strLocation);
        initStrategies(applicationContext);

    }

    private void initStrategies(FZWebApplicationContext context) {
        initHandlerMappings(context);//通过HandlerMapping，将请求映射到处理器
        initHandlerAdapters(context);//通过HandlerAdapter进行多类型的参数动态匹配
        initViewResolvers(context);//通过viewResolver解析逻辑视图到具体视图实现
    }

    private void initHandlerMappings(FZWebApplicationContext context) {
        String[] beanNames = context.getBeanDefinitions();
        for (String beanName : beanNames) {
            FZBeanWrapper beanWrapper = (FZBeanWrapper) context.getBean(beanName);

            if (!beanWrapper.get_originalBean().getClass().isAnnotationPresent(FZController.class)) {
                continue;
            }
            Class<?> clazz = beanWrapper.get_originalBean().getClass();
            String strBaseUrl = "";
            if (clazz.isAnnotationPresent(FZRequestMapping.class)) {
                FZRequestMapping classRM = clazz.getAnnotation(FZRequestMapping.class);
                strBaseUrl = classRM.value().trim();
            }

            //Controller扫描之后,接着扫描其Method
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(FZRequestMapping.class)) {
                    continue;
                }

                FZRequestMapping methodRM = method.getAnnotation(FZRequestMapping.class);
                String methodUrl = methodRM.value().trim();
                String strTotalUrl = ("/" + strBaseUrl + methodUrl.replaceAll("\\*", ".*")).replaceAll("/+", "/");
                this.handlerMappings.add(new FZHandlerMapping(beanWrapper.get_originalBean(), method, Pattern.compile(strTotalUrl)));
                System.out.println("Mapping: " + strTotalUrl + " , " + method);
            }


        }
    }

    private void initHandlerAdapters(FZWebApplicationContext context) {
        for (FZHandlerMapping handlerMapping : this.handlerMappings) {
            Method method = handlerMapping.getMethod();
            Map<String, Integer> methodParamMapping = new HashMap<>();

            Annotation[][] paras = method.getParameterAnnotations();

            //先处理命名参数
            for (int i = 0; i < paras.length; i++) {
                for (Annotation a : paras[i]) {
                    if (a instanceof FZRequestParam) {
                        String paraName = ((FZRequestParam) a).value().trim();
                        methodParamMapping.put(paraName, i);
                        break;
                    }
                }
            }

            //再处理request, response参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == HttpServletRequest.class || parameterTypes[i] == HttpServletResponse.class) {
                    //记录下method形参中request,response两种参数的位置
                    methodParamMapping.put(parameterTypes[i].getName(), i);
                }
            }


            this.handlerAdapterMap.put(handlerMapping, new FZHandlerAdapter(handlerMapping, methodParamMapping));
            System.out.println("initHandlerAdapters: " + methodParamMapping + " , " + method);
        }
    }

    private void initViewResolvers(FZWebApplicationContext context) {
        String fileRootPath = context.getConfig().getProperty("templateRoot");
        String absolutePaths = this.getClass().getClassLoader().getResource(fileRootPath).getFile();

        File viewDirectory = new File(absolutePaths);
        for (File file : viewDirectory.listFiles()) {
            this.viewResolvers.add(new FZViewResolver(file.getName(), file));
        }
    }


    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws InstantiationException, IllegalAccessException {
        FZHandlerMapping handlerMapping = getHandler(request, response);
        if(handlerMapping==null){
            try {
                response.getWriter().write("404 not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        FZHandlerAdapter handlerAdapter = getHandlerAdapter(handlerMapping);
        FZModelAndView viewAndModel = handlerAdapter.handler(request, response);
        processDispatcherResult(request, response, viewAndModel);
    }

    private void processDispatcherResult(HttpServletRequest request, HttpServletResponse response, FZModelAndView viewAndModel) {

        try {
            if (viewAndModel == null) {
                response.getWriter().write("404 not found, please try again");
                return;
            }
            for (FZViewResolver viewResolver : this.viewResolvers) {
                if (viewAndModel.getViewName().equals(viewResolver.getViewName())) {
                    String strResult = viewResolver.processViews(viewAndModel);
                    strResult = strResult == null ? "" : strResult;
                    response.getWriter().write(strResult);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FZHandlerAdapter getHandlerAdapter(FZHandlerMapping handlerMapping) {
        if (handlerMapping == null) {
            return null;
        }
        return this.handlerAdapterMap.get(handlerMapping);
    }


    private FZHandlerMapping getHandler(HttpServletRequest request, HttpServletResponse response) {
        if (this.handlerMappings.size() < 1) {
            return null;
        }

        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replace(contextPath, "").replace("/+", "/");
        for (FZHandlerMapping handlerMapping : this.handlerMappings) {
            if (handlerMapping.getUrlPattern().matcher(url).matches()) {
                return handlerMapping;
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
