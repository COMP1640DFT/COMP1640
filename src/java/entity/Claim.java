
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
    private String createDate;
    private String endDate;
    private String filedata;
    private String userFullName;
    private String idUser;
    private String facultyName;
    private String assessmentName;
    private String itemAssessmentName;
    private int idCM;
    private int idItemAssessment;
    private int status;
    private int statusCM;
    private Decision decision;
    private String className;
    private List<Claim> listClaimWithoutEvidence;
    private List<Claim> listClaimUnresolved;
    private List<Claim> listClaim;
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

    public Claim(String title, String content, String sendDate, String filedata, String idUser, int idCM, int status) {
        this.title = title;
        this.content = content;
        this.sendDate = sendDate;
        this.filedata = filedata;
        this.idUser = idUser;
        this.idCM = idCM;
        this.status = status;
    }
    
    public Claim(String title, String content, String sendDate, String filedata, String idUser, int idCM, int status,int idItemAssessment) {
        this.title = title;
        this.content = content;
        this.sendDate = sendDate;
        this.filedata = filedata;
        this.idUser = idUser;
        this.idCM = idCM;
        this.status = status;
        this.idItemAssessment=idItemAssessment;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getidItemAssessment() {
        return idItemAssessment;
    }

    public void setidItemAssessment(int idItemAssessment) {
        this.idItemAssessment = idItemAssessment;
    }
    

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getItemAssessmentName() {
        return itemAssessmentName;
    }

    public void setItemAssessmentName(String itemAssessmentName) {
        this.itemAssessmentName = itemAssessmentName;
    }

    public int getIdItemAssessment() {
        return idItemAssessment;
    }

    public void setIdItemAssessment(int idItemAssessment) {
        this.idItemAssessment = idItemAssessment;
    }
    

    public int getIdCM() {
        return idCM;
    }

    public List<Claim> getListClaim() {
        return listClaim;
    }

    public void setListClaim(List<Claim> listClaim) {
        this.listClaim = listClaim;
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

    public int getStatusCM() {
        return statusCM;
    }

    public void setStatusCM(int statusCM) {
        this.statusCM = statusCM;
    }
    
    
}
