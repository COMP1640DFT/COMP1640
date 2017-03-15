
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
     private String filedata;
    private String userFullName;
    private String idUser;
    private int idCM;
    private int status;
    private Decision decision;
    private String className;
    private List<Claim> listClaimWithoutEvidence;
    private List<Claim> listClaimUnresolved;
    private List<ItemSelected> listSelectedMajor;

    public List<ItemSelected> getListSelectedMajor() {
        return listSelectedMajor;
    }

    public void setListSelectedMajor(List<ItemSelected> listSelectedMajor) {
        this.listSelectedMajor = listSelectedMajor;
    }
    
    public String getFiledata() {
        return filedata;
    }

    public void setFiledata(String filedata) {
        this.filedata = filedata;
    }
    
    
     public Claim() {
    }

    public Claim(int idClaim, String title, String content, String date, String filedata, String idUser, int idCM) {
        this.idClaim = idClaim;
        this.title = title;
        this.content = content;
        this.sendDate = date;
        this.filedata = filedata;
        this.idUser = idUser;
        this.idCM = idCM;

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

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public List<Claim> getListClaimWithoutEvidence() {
        return listClaimWithoutEvidence;
    }

    public void setListClaimWithoutEvidence(List<Claim> listClaimWithoutEvidence) {
        this.listClaimWithoutEvidence = listClaimWithoutEvidence;
    }

    public List<Claim> getListClaimUnresolved() {
        return listClaimUnresolved;
    }

    public void setListClaimUnresolved(List<Claim> listClaimUnresolved) {
        this.listClaimUnresolved = listClaimUnresolved;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
