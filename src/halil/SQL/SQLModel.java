/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halil.SQL;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author halil
 */
public class SQLModel {

    protected Integer id;
    protected String tableName;
    protected String[] databaseFields;

    private Object getField(String fieldName) {
        try {
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method o = this.getClass().getMethod("get" + fieldName, null);
            return o.invoke(this);
        } catch (Exception e) {
            System.err.format("%s Classındaki %s Fieldi Çekilirken Hata\n", this.getClass().getName(), fieldName);
            return "";
        }
    }

    public HashMap<String, Object> getFields() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        for (String databaseField : databaseFields) {
            Object gelenobje = getField(databaseField);
            fields.put(databaseField, gelenobje);
        }
        return fields;
    }

    public void save() {
        try {
            if (this.id != null) {
                update();
            } else {
                this.id = (Integer) insert();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.format("%s Classı Veritabanına Kaydedilirken Hata\n", this.getClass().getName());
        }
    }

    private Object insert() throws SQLException {
        HashMap<String, Object> Keys = getFields();
        SQLQueryType sqlType = SQLQueryType.INSERT;
        String query = SQLQuery.queryBuild(tableName, sqlType, Keys);
        System.out.println(query);
        return SQLQuery.query(query, sqlType, Keys.values().toArray());
    }

    private Object update() throws SQLException {
        HashMap<String, Object> Keys = getFields();
        SQLQueryType sqlType = SQLQueryType.UPDATE;
        String query = SQLQuery.queryBuildWithWhere(tableName, sqlType, Keys);
        return SQLQuery.query(query, sqlType, this.id, Keys.values().toArray());
    }

    public Object select() throws Exception {
        HashMap<String, Object> Keys = getFields();
        SQLQueryType sqlType = SQLQueryType.SELECT;
        String query = SQLQuery.queryBuild(tableName, sqlType, null);
        return SQLQuery.query(query, sqlType, null);
    }

    public Integer getId() {
        return id;
    }

}
