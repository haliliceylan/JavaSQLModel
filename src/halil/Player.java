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
public class Player extends SQLModel {

    private String name;
    private String surname;
    private String username;
    private String cokgizliveritabanindabileolmayacak;
    private int faction_id;

    public Player() {
        this.databaseFields = new String[]{"name", "surname", "username", "faction_id"};
        this.tableName = "players";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCokgizliveritabanindabileolmayacak() {
        return cokgizliveritabanindabileolmayacak;
    }

    public void setCokgizliveritabanindabileolmayacak(String cokgizliveritabanindabileolmayacak) {
        this.cokgizliveritabanindabileolmayacak = cokgizliveritabanindabileolmayacak;
    }

    public int getFaction_id() {
        return faction_id;
    }

    public void setFaction_id(int faction_id) {
        this.faction_id = faction_id;
    }
    
    
}
