/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author DaoMinhThien
 */
public class Faculty implements Serializable {
    private int id;
    private String name;
    private List<Faculty> listFaculty;
    

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

    public List<Faculty> getListFacultys() {
        return listFaculty;
    }

    public void setListFacultys(List<Faculty> listFaculty) {
        this.listFaculty = listFaculty;
    }
}
