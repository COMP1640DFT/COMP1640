/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author dungkv
 */
public class Claim {
    private int idClaim;
    private String title;
    private String content;
    private String sendDate;
    private String userFullName;
    private String className;
    List<Claim> list;

    public List<Claim> getList() {
        return list;
    }

    public void setList(List<Claim> list) {
        this.list = list;
    }
    
    public int getIdClaim() {
        return idClaim;
    }

    public void setIdClaim(int idClaim) {
        this.idClaim = idClaim;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
}
