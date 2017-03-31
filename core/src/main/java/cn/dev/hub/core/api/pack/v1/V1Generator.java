package cn.dev.hub.core.api.pack.v1;

import cn.dev.hub.core.api.pack.BaseGenerator;
import cn.dev.hub.core.api.pack.Table;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static cn.dev.hub.core.utils.IOUtils.createFiles;
import static cn.dev.hub.core.utils.StringUtils.*;

/**
 * Created by suzunshou on 2017/3/31.
 */
public class V1Generator extends BaseGenerator {
    @Override
    protected void generate(String packageName, VelocityEngine ve, List<Table> list, int i) throws IOException {
        Table table = list.get(i);
        String className = upperFirstCharacter(table.getTableName());
        generateBean(ve, "tpl/com/v1/javabean.vm", table, packageName, className, getWriter());
        genetateDao(ve, "tpl/com/v1/dao.vm", packageName, className, getWriter());
        genetateMapper(ve, "tpl/com/v1/mapper.vm", table, packageName, className, getWriter());
        generateIService(ve, "tpl/com/v1/iservice.vm", packageName, className, getWriter());
        generateServiceImpl(ve, "tpl/com/v1/serviceImpl.vm", packageName, className, getWriter());
        generateController(ve, "tpl/com/v1/controller.vm", table, packageName, className, getWriter());
        generateSpringApplicationContextXml(ve, "tpl/com/v1/applicationContext.vm", table, packageName, className, getWriter());
        generateSpringDispatcherServletXml(ve, "tpl/com/v1/springDispatcherServlet.vm", table, packageName, className, getWriter());
        generateDbProperties(ve, "tpl/com/v1/dbProperties.vm", table, packageName, className, getWriter());
        generateWebXml(ve, "tpl/com/v1/webxml.vm", table, packageName, className, getWriter());
    }

    private void generateDbProperties(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "properties/db.properties";
        createFiles(writer, fileName);
    }

    private void generateWebXml(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "xml/web.xml";
        createFiles(writer, fileName);
    }

    private void generateSpringDispatcherServletXml(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "xml/springDispatcherServlet-servlet.xml";
        createFiles(writer, fileName);
    }

    private void generateSpringApplicationContextXml(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "xml/applicationContext.xml";
        createFiles(writer, fileName);
    }

    private void generateBean(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("map", table.getPropertyMap());
        context.put("importSet", table.getImportSet());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "bean/" + className + ".java";
        createFiles(writer, fileName);
    }

    private void generateVo(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("map", table.getPropertyMap());
        context.put("importSet", table.getImportSet());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "bean/vo/" + className + "Vo.java";
        createFiles(writer, fileName);
    }

    private void generatePo(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("map", table.getPropertyMap());
        context.put("importSet", table.getImportSet());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "bean/po/" + className + "Po.java";
        createFiles(writer, fileName);
    }

    private void genetateDao(VelocityEngine ve, String tpl, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "dao/" + className + "Mapper" + ".java";
        createFiles(writer, fileName);
    }

    private void genetateMapper(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("columnMap", table.getColumnMap());
        context.put("primaryKey", table.getPrimaryKey());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "mapper/" + className + "Mapper" + ".xml";
        createFiles(writer, fileName);
    }

    private void generateIService(VelocityEngine ve, String tpl, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "service/api/" + "I" + className + "Service" + ".java";
        createFiles(writer, fileName);
    }

    private void generateServiceImpl(VelocityEngine ve, String tpl, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "service/impl/" + className + "ServiceImpl" + ".java";
        createFiles(writer, fileName);
    }

    private void generateController(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("map", table.getPropertyMap());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "controller/" + className + "Controller" + ".java";
        createFiles(writer, fileName);
    }
}
