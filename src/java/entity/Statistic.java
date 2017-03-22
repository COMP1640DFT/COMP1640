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
    private String year;
    private String major;
    private String user;
   
    private List<Statistic>  listStatisticAllMajor;
    private List<Statistic>  listNumOfStudent;
    private List<Statistic>  listNumOfClaim;
    private List<ItemSelected> listItemYear;
    private List<ItemSelected> listItemMajor;

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

    public List<ItemSelected> getListItemYear() {
        return listItemYear;
    }

    public void setListItemYear(List<ItemSelected> listItemYear) {
        this.listItemYear = listItemYear;
    }

    public List<ItemSelected> getListItemMajor() {
        return listItemMajor;
    }

    public void setListItemMajor(List<ItemSelected> listItemMajor) {
        this.listItemMajor = listItemMajor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    
    
    
    public int getData() {
        return data;
    }

    public void setData(int count) {
        this.data = count;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
}
