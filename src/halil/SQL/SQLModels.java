/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halil.SQL;

import halil.Player;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author halil
 */
public class SQLModels {

    private static HashMap<Class, ArrayList<SQLModel>> Models = new HashMap<Class, ArrayList<SQLModel>>();

    /*
    id'yi set edecek
     */
    public static void loadClass(SQLModel Example) {
        try {
            
            if (!Models.containsKey(Example.getClass())) {
                Models.put(Example.getClass(), new ArrayList<SQLModel>());
            }
            ArrayList<SQLModel> list = Models.get(Example.getClass());
            ResultSet rs = (ResultSet) Example.select();
            while (rs.next()) {
                SQLModel temp = Example.getClass().getConstructor(null).newInstance(null);
                temp.id = rs.getInt("id");
                for (String field : Example.databaseFields) {
                    switch (temp.getClass().getMethod("get"+field.substring(0, 1).toUpperCase() + field.substring(1)).getReturnType().getSimpleName()) {
                        case "String":
                            temp.getClass().getMethod("set" + field.substring(0, 1).toUpperCase() + field.substring(1), String.class).invoke(temp, rs.getString(field));
                            break;
                        case "Integer":
                            temp.getClass().getMethod("set" + field.substring(0, 1).toUpperCase() + field.substring(1), Integer.class).invoke(temp, rs.getInt(field));
                            break;
                    }
                }
                list.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static SQLModel get(Class Class, String field, Object equals) {
        for (Object Model : Models.get(Class)) {
            try {
                if (equals.equals(Class.getMethod("get" + field.substring(0, 1).toUpperCase() + field.substring(1), null).invoke(Model, null))) {
                    return (SQLModel) Model;
                };
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    // Berat Söylenmeyi bırak aq nere gittin amk
}
