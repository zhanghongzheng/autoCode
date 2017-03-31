package cn.dev.hub.core.api.pack.topnews;

import cn.dev.hub.core.api.pack.BaseGenerator;
import cn.dev.hub.core.api.pack.Table;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static cn.dev.hub.core.utils.IOUtils.createFiles;
import static cn.dev.hub.core.utils.StringUtils.getWriter;
import static cn.dev.hub.core.utils.StringUtils.returnFilePath;
import static cn.dev.hub.core.utils.StringUtils.upperFirstCharacter;

/**
 * Created by suzunshou on 2017/3/29.
 */
public class TopnewsGenerator extends BaseGenerator {
    @Override
    protected void generate(String packageName, VelocityEngine ve, List<Table> list, int i) throws IOException {
        Table table = list.get(i);
        String className = upperFirstCharacter(table.getTableName());

        //business-api
        generateBusinessApiVo(ve, "tpl/com/topnews/business/api/vo/vo.vm", table, packageName, className, getWriter());
        generateBusinessApiService(ve, "tpl/com/topnews/business/api/service/iservice.vm", table, packageName, className, getWriter());

        //business-service
        generateBusinessServiceImpl(ve, "tpl/com/topnews/business/impl/serviceImpl.vm", table, packageName, className, getWriter());

        //dao
        generateDaoPo(ve, "tpl/com/topnews/dao/po/po.vm", table, packageName, className, getWriter());
        genetateDaoMapper(ve, "tpl/com/topnews/dao/mapper/mapper.vm", packageName, className, getWriter());
        genetateDaoMybatis(ve, "tpl/com/topnews/dao/mybatis/mybatis.vm", table, packageName, className, getWriter());

        //core
        generateCoreApi(ve, "tpl/com/topnews/core/api/iservice.vm", packageName, className, getWriter());
        generateCoreImpl(ve, "tpl/com/topnews/core/impl/serviceImpl.vm", packageName, className, getWriter());

        //mvc
        generateMVCController(ve, "tpl/com/topnews/mvc/controller.vm", table, packageName, className, getWriter());
    }

    private void generateBusinessApiVo(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("map", table.getPropertyMap());
        context.put("importSet", table.getImportSet());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "business/api/vo/" + className + "Vo.java";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }

    private void generateBusinessApiService(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "business/api/service/I" + className + "ApiService.java";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }

    private void generateBusinessServiceImpl(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "business/api/impl/" + className + "ApiServiceImpl.java";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }

    private void generateDaoPo(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("map", table.getPropertyMap());
        context.put("importSet", table.getImportSet());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "dao/po/" + className + "Po.java";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }

    private void genetateDaoMapper(VelocityEngine ve, String tpl, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "dao/mapper/" + className + "Mapper.java";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }

    private void genetateDaoMybatis(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("columnMap", table.getColumnMap());
        context.put("primaryKey", table.getPrimaryKey());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "dao/mybatis/" + className + "Mapper.xml";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }

    private void generateCoreApi(VelocityEngine ve, String tpl, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "core/api/I" + className + "Service.java";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }

    private void generateCoreImpl(VelocityEngine ve, String tpl, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "core/impl/" + className + "ServiceImpl.java";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }

    private void generateMVCController(VelocityEngine ve, String tpl, Table table, String packageName, String className, StringWriter writer) throws IOException {
        VelocityContext context = new VelocityContext();
        context.put("packageName", packageName);
        context.put("className", className);
        context.put("map", table.getPropertyMap());
        Template t = ve.getTemplate(tpl, "utf-8");
        t.merge(context, writer);
        String fileName = "./res/" + returnFilePath(packageName) + "controller/" + className + "Controller" + ".java";
        createFiles(writer, fileName);
        System.out.println("generate " + fileName);
    }
}
