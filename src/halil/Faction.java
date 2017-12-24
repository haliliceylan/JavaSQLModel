/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package halil;

import halil.SQL.SQLModel;

/**
 *
 * @author halil
 */
public class Faction extends SQLModel{
    private String factionName;

    public Faction(String factionName) {
        this.id = 1;
        this.factionName = factionName;
        this.databaseFields= new String[]{"id","factionName"};
        this.tableName = "factions";
    }

    public String getFactionName() {
        return factionName;
    }

    public void setFactionName(String factionName) {
        this.factionName = factionName;
    }
    
    
    
}
