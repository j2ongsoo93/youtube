package topia.com.myApp.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer userIdx;
    private String userId;
    private String userPwd;
    private String userName;
    private String userPhone;
    private String userEmail;
    private Integer companyIdx;
    private Integer deptId;
    private Date userRegDate;
    private String userRole;
    private String userJob;
    private String userPosition;

    public UserInfo() {
    }

    public UserInfo(Integer userIdx, String userId, String userPwd, String userName, String userPhone, String userEmail, Integer companyIdx, Integer deptId, Date userRegDate, String userRole, String userJob, String userPosition) {
        this.userIdx = userIdx;
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.companyIdx = companyIdx;
        this.deptId = deptId;
        this.userRegDate = userRegDate;
        this.userRole = userRole;
        this.userJob = userJob;
        this.userPosition = userPosition;
    }

    public Integer getUserIdx() {
        return userIdx;
    }

    public void setUserIdx(Integer userIdx) {
        this.userIdx = userIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getCompanyIdx() {
        return companyIdx;
    }

    public void setCompanyIdx(Integer companyIdx) {
        this.companyIdx = companyIdx;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getUserRegDate() {
        return userRegDate;
    }

    public void setUserRegDate(Date userRegDate) {
        this.userRegDate = userRegDate;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserJob() {
        return userJob;
    }

    public void setUserJob(String userJob) {
        this.userJob = userJob;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }
}
