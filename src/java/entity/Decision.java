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
public class Decision {
    private int idClaim;
    private String content;
    private String sendDate;
    private int status;
    private String idUser;
    private String idUserEC;
    private String fullNameEC;
    private String claimTitle;
    public Decision() {
    }

    public Decision(int idClaim, String content, String sendDate, int status, String idUser) {
        this.idClaim = idClaim;
        this.content = content;
        this.sendDate = sendDate;
        this.status = status;
        this.idUser = idUser;
    }

    public int getIdClaim() {
        return idClaim;
    }

    public void setIdClaim(int idClaim) {
        this.idClaim = idClaim;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdUserEC() {
        return idUserEC;
    }

    public void setIdUserEC(String idUserEC) {
        this.idUserEC = idUserEC;
    }

    public String getFullNameEC() {
        return fullNameEC;
    }

    public void setFullNameEC(String fullNameEC) {
        this.fullNameEC = fullNameEC;
    }

    public String getClaimTitle() {
        return claimTitle;
    }

    public void setClaimTitle(String claimTitle) {
        this.claimTitle = claimTitle;
    }
    
    
    
}
