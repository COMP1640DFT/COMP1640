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
public class Statistic {
    private int data;
    private String title;
    private List<Statistic>  listStatisticAllMajor;
    private List<Statistic>  listNumOfStudent;
    private List<Statistic>  listNumOfClaim;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Statistic> getListStatisticAllMajor() {
        return listStatisticAllMajor;
    }

    public void setListStatisticAllMajor(List<Statistic> listStatisticAllMajor) {
        this.listStatisticAllMajor = listStatisticAllMajor;
    }

    public List<Statistic> getListNumOfStudent() {
        return listNumOfStudent;
    }

    public void setListNumOfStudent(List<Statistic> listNumOfStudent) {
        this.listNumOfStudent = listNumOfStudent;
    }

    public List<Statistic> getListNumOfClaim() {
        return listNumOfClaim;
    }

    public void setListNumOfClaim(List<Statistic> listNumOfClaim) {
        this.listNumOfClaim = listNumOfClaim;
    }
    
    
    
    public int getData() {
        return data;
    }

    public void setData(int count) {
        this.data = count;
    }
}
