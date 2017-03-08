/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author user
 */
public class Claim {

    private int idClaim;
    private String title;
    private String content;
    private String date;
    private String filedata;
    private String idUser;
    private int idCM;
    private String fullnameS;
    private Decision decision;

    public String getFullnameS() {
        return fullnameS;
    }

    public void setFullnameS(String fullnameS) {
        this.fullnameS = fullnameS;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Claim() {
    }

    public Claim(int idClaim, String title, String content, String date, String filedata, String idUser, int idCM) {
        this.idClaim = idClaim;
        this.title = title;
        this.content = content;
        this.date = date;
        this.filedata = filedata;
        this.idUser = idUser;
        this.idCM = idCM;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFiledata() {
        return filedata;
    }

    public void setFiledata(String filedata) {
        this.filedata = filedata;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getIdCM() {
        return idCM;
    }

    public void setIdCM(int idCM) {
        this.idCM = idCM;
    }
}
