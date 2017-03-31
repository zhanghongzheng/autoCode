package cn.dev.hub.core.api.pack;

import java.util.Map;
import java.util.Set;

/**
 * Created by suzunshou on 2017/3/31.
 */
public class Table {
    private String tableName;
    private Map<String, String> propertyMap;
    private Map<String, String> columnMap;
    private Set importSet;
    private String primaryKey;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Set getImportSet() {
        return importSet;
    }

    public void setImportSet(Set importSet) {
        this.importSet = importSet;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Map<String, String> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, String> columnMap) {
        this.columnMap = columnMap;
    }
}