/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author DaoMinhThien
 */
public class Assessment {
    private int id;
    private String name;
    private int idFaculty;

    public Assessment(int id, String name, int idFaculty) {
        this.id = id;
        this.name = name;
        this.idFaculty = idFaculty;
    }

    public Assessment() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }
    
    
}
