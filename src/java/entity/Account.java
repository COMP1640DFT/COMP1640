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
public class Account {
    private String idUser;
    private String _passWord;
    private String fullName;
    private String dob;
    private String email;
    private String phoneNumber;
    private int idAcademy;
    private int idFaculty;
    private int lever;

    public Account() {
    }

    public Account(String idUser, String _passWord, String fullName, String dob, String email, String phoneNumber, int idAcademy, int idFaculty, int lever) {
        this.idUser = idUser;
        this._passWord = _passWord;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idAcademy = idAcademy;
        this.idFaculty = idFaculty;
        this.lever = lever;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassWord() {
        return _passWord;
    }

    public void setPassWord(String _passWord) {
        this._passWord = _passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIdAcademy() {
        return idAcademy;
    }

    public void setIdAcademy(int idAcademy) {
        this.idAcademy = idAcademy;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }
    
    
}
