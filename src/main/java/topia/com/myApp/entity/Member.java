package topia.com.myApp.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Member {
    private String memId;
    private String memName;
    private String password;
    private Date registDate;
    private Date updateDate;
    private String registId;
    private String updateId;
    private String registIp;
    private String updateIp;
    private String memProfile;
    private String memEmail;
    private boolean enabled;

    public Member() {
    }

    public Member(String memId, String memName, String password, Date registDate, Date updateDate, String registId, String updateId, String registIp, String updateIp, String memProfile, String memEmail, boolean enabled) {
        this.memId = memId;
        this.memName = memName;
        this.password = password;
        this.registDate = registDate;
        this.updateDate = updateDate;
        this.registId = registId;
        this.updateId = updateId;
        this.registIp = registIp;
        this.updateIp = updateIp;
        this.memProfile = memProfile;
        this.memEmail = memEmail;
        this.enabled = enabled;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRegistId() {
        return registId;
    }

    public void setRegistId(String registId) {
        this.registId = registId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getRegistIp() {
        return registIp;
    }

    public void setRegistIp(String registIp) {
        this.registIp = registIp;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    public String getMemProfile() {
        return memProfile;
    }

    public void setMemProfile(String memProfile) {
        this.memProfile = memProfile;
    }

    public String getMemEmail() {
        return memEmail;
    }

    public void setMemEmail(String memEmail) {
        this.memEmail = memEmail;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
