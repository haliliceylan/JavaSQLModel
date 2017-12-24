/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halil;

import halil.SQL.Config;
import halil.SQL.SQLModels;

/**
 *
 * @author halil
 */
public class Halil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Config.mysqlConnect();
        SQLModels.loadClass(new Player());
    }

}
