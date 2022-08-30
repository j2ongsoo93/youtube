package topia.com.myApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public class YtbReply {
    private Integer reIdx;
    private String memId;
    private String reContent;
    private Date reRegDate;
    private Date reUpdateDate;
    private Integer ytbIdx;
    private String imgFileName;

    public YtbReply() {
    }

    public YtbReply(Integer reIdx, String memId, String reContent, Date reRegDate, Date reUpdateDate, Integer ytbIdx) {
        this.reIdx = reIdx;
        this.memId = memId;
        this.reContent = reContent;
        this.reRegDate = reRegDate;
        this.reUpdateDate = reUpdateDate;
        this.ytbIdx = ytbIdx;
    }

    public Integer getReIdx() {
        return reIdx;
    }

    public void setReIdx(Integer reIdx) {
        this.reIdx = reIdx;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getReContent() {
        return reContent;
    }

    public void setReContent(String reContent) {
        this.reContent = reContent;
    }

    public Date getReRegDate() {
        return reRegDate;
    }

    public void setReRegDate(Date reRegDate) {
        this.reRegDate = reRegDate;
    }

    public Date getReUpdateDate() {
        return reUpdateDate;
    }

    public void setReUpdateDate(Date reUpdateDate) {
        this.reUpdateDate = reUpdateDate;
    }

    public Integer getYtbIdx() {
        return ytbIdx;
    }

    public void setYtbIdx(Integer ytbIdx) {
        this.ytbIdx = ytbIdx;
    }
}
