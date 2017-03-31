package cn.dev.hub.core.api.pack;

import cn.dev.hub.core.utils.DBUtils;
import cn.dev.hub.core.utils.JDBCTypesUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Created by suzunshou on 2017/3/31.
 */
public abstract class BaseGenerator {
    public void init(String packageName) {
        try {
            // 初始化并取得Velocity引擎
            VelocityEngine ve = new VelocityEngine();
            // 取得velocity的模版
            ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
            ve.init();

            List<Table> list = getTableInfo(DBUtils.dbName);
            for (int i = 0; i < list.size(); i++) {
                generate(packageName, ve, list, i);
            }
            System.out.println(">>Generate Code Success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void generate(String packageName, VelocityEngine ve, List<Table> list, int i) throws IOException {

    }

    private List<Table> getTableInfo(String dbName) {
        List<Table> tables = new ArrayList<>();
        Connection connection = null;
        ResultSet rest = null;
        try {
            connection = DBUtils.getConnection();
            DatabaseMetaData dbmd = connection.getMetaData();
            rest = dbmd.getTables(dbName, null, null, new String[]{"TABLE"});
            while (rest.next()) {
                String tableName = rest.getString("TABLE_NAME");
                ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
                Table table = new Table();
                table.setTableName(tableName);
                Map propertyMap = new HashMap();
                Map columnMap = new HashMap();
                Set importSet = new HashSet();
                ResultSet pkSet = dbmd.getPrimaryKeys(null, null, tableName);
                while (pkSet.next()) {
                    table.setPrimaryKey(pkSet.getString("COLUMN_NAME"));
                }
                while (rs.next()) {
                    String strFieldName = rs.getString(4);
                    int strFieldType = rs.getInt(5);
                    columnMap.put(strFieldName, JDBCTypesUtils.jdbcTypeValues.get(strFieldType));
                    propertyMap.put(strFieldName, JDBCTypesUtils.jdbcTypeToJavaType(strFieldType).getSimpleName());
                    if (strFieldType == Types.DATE || strFieldType == Types.TIME || strFieldType == Types.TIMESTAMP || strFieldType == Types.NUMERIC || strFieldType == Types.DECIMAL || strFieldType == Types.FLOAT || strFieldType == Types.REAL || strFieldType == Types.DOUBLE) {
                        importSet.add(JDBCTypesUtils.jdbcTypeToJavaType(strFieldType).getName());
                    }
                }
                table.setColumnMap(columnMap);
                table.setPropertyMap(propertyMap);
                table.setImportSet(importSet);
                tables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeDB(rest, null, connection);
        }
        return tables;
    }

}
