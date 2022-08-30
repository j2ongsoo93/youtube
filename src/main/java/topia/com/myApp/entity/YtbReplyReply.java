package topia.com.myApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class YtbReplyReply {
    private Integer rereIdx;
    private Integer reIdx;
    private String memId;
    private String rereContent;
    private Date rereRegDate;
    private Date rereUpdateDate;
    private String rereAnnotation;

    public YtbReplyReply() {
    }

    public YtbReplyReply(Integer rereIdx, Integer reIdx, String memId, String rereContent, Date rereRegDate, Date rereUpdateDate, String rereAnnotation) {
        this.rereIdx = rereIdx;
        this.reIdx = reIdx;
        this.memId = memId;
        this.rereContent = rereContent;
        this.rereRegDate = rereRegDate;
        this.rereUpdateDate = rereUpdateDate;
        this.rereAnnotation = rereAnnotation;
    }

    public Integer getRereIdx() {
        return rereIdx;
    }

    public void setRereIdx(Integer rereIdx) {
        this.rereIdx = rereIdx;
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

    public String getRereContent() {
        return rereContent;
    }

    public void setRereContent(String rereContent) {
        this.rereContent = rereContent;
    }

    public Date getRereRegDate() {
        return rereRegDate;
    }

    public void setRereRegDate(Date rereRegDate) {
        this.rereRegDate = rereRegDate;
    }

    public String getRereAnnotation() {
        return rereAnnotation;
    }

    public void setRereAnnotation(String rereAnnotation) {
        this.rereAnnotation = rereAnnotation;
    }

    public Date getRereUpdateDate() {
        return rereUpdateDate;
    }

    public void setRereUpdateDate(Date rereUpdateDate) {
        this.rereUpdateDate = rereUpdateDate;
    }
}
