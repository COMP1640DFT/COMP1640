/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author DaoMinhThien
 */
public class AsssessmentDetail {
    private int id;
    private int idAssessment;
    private int idItem;
    
    private List<ItemSelected> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAssessment() {
        return idAssessment;
    }

    public void setIdAssessment(int idAssessment) {
        this.idAssessment = idAssessment;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public List<ItemSelected> getList() {
        return list;
    }

    public void setList(List<ItemSelected> list) {
        this.list = list;
    }
    
    
    
    
            
           
}
