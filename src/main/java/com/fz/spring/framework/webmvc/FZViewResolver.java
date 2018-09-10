package com.fz.spring.framework.webmvc;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FZViewResolver {
    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public File getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(File templateFile) {
        this.templateFile = templateFile;
    }

    private String viewName;
    private File templateFile;

    public FZViewResolver(String viewName, File templateFile) {
        this.viewName = viewName;
        this.templateFile = templateFile;
    }

    public String processViews(FZModelAndView mv) throws  Exception{

        RandomAccessFile randomAccessFile  = new RandomAccessFile(this.templateFile,"r");
        StringBuilder sb = new StringBuilder();
        String strLine ="";
        while ((strLine=randomAccessFile.readLine())!=null){
//            strLine = new String(strLine.getBytes("ISO-8859-1"), "utf-8");
            Matcher m = matchLine(strLine);
            while (m.find())
            {
                for (int i = 0; i < m.groupCount(); i++) {
                    String paraName = m.group(i);
                    paraName=paraName.replace("${","").replace("}","");
                    Object paraValue = mv.getDataModel().get(paraName);
                    if(paraValue==null){
                        continue;
                    }

                    strLine=strLine.replaceAll("\\$\\{"+paraName+"\\}",paraValue.toString());
//                    strLine = new String(strLine.getBytes("utf-8"), "ISO-8859-1");
                }

            }
            sb.append(strLine);
        }
        randomAccessFile.close();
        return sb.toString();

    }
    private Matcher matchLine(String strLine){
//        Pattern pattern=Pattern.compile("$\\{(.*)\\}",Pattern.CASE_INSENSITIVE);
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}",Pattern.CASE_INSENSITIVE);
        return pattern.matcher(strLine);
    }

}
