/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halil.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author halil
 */
public class SQLQuery {

    public static Object query(String queryN, SQLQueryType queryType, Object[] variables) throws SQLException {
        return query(queryN, queryType, 0, variables);
    }

    public static Object query(String queryN, SQLQueryType queryType, Integer id, Object[] variables) throws SQLException {
        PreparedStatement stm = Config.con.prepareStatement(queryN, Statement.RETURN_GENERATED_KEYS);
        if (variables != null) {
            for (int i = 0; i < variables.length; i++) {
                switch (variables[i].getClass().getSimpleName()) {
                    case "String":
                        stm.setString(i + 1, (String) variables[i]);
                        break;
                    case "Integer":
                        stm.setInt(i + 1, (Integer) variables[i]);
                        break;
                    default:
                        stm.setObject(i + 1, variables[i]);
                }
            }
        }
        switch (queryType) {
            case SELECT:
                return stm.executeQuery();
            case UPDATE:
            case DELETE:
                stm.setInt(variables.length + 1, id);
            default:
                return stm.executeUpdate();
            case INSERT:
                stm.executeUpdate();
                ResultSet rs = stm.getGeneratedKeys();
                if (rs != null & rs.next()) {
                    return rs.getInt(1);
                } else {
                    return 0;
                }
        }
    }

    public static String queryBuildWithWhere(String tableName, SQLQueryType queryType, HashMap<String, Object> fields) {
        return String.format("%s WHERE id = ?", queryBuild(tableName, queryType, fields));
    }

    public static String queryBuild(String tableName, SQLQueryType queryType, HashMap<String, Object> fields) {
        switch (queryType) {
            case DELETE:
                return deleteQueryBuild(tableName, fields);
            case INSERT:
                return insertQueryBuild(tableName, fields);
            case SELECT:
                return selectQueryBuild(tableName, fields);
            case UPDATE:
                return updateQueryBuild(tableName, fields);
            default:
                return null;
        }
    }

    private static String selectQueryBuild(String tableName, HashMap<String, Object> fields) {
        String query = String.format("SELECT * FROM %s", tableName);
        return query;
    }

    private static String insertQueryBuild(String tableName, HashMap<String, Object> fields) {
        return String.format("INSERT INTO %s (%s) VALUES (%s)",
                tableName,
                String.join(", ", fields.keySet()),
                String.join(", ", Collections.nCopies(fields.keySet().size(), "?")));
    }

    private static String updateQueryBuild(String tableName, HashMap<String, Object> fields) {
        return String.format("UPDATE %s SET %s = ?",
                tableName,
                String.join(" = ?, ", fields.keySet()));
    }

    private static String deleteQueryBuild(String tableName, HashMap<String, Object> fields) {
        return String.format("DELETE FROM %s",
                tableName);
    }

}
