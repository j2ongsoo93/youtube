package topia.com.myApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberProfileImg {
    private Integer imgIdx;
    private String imgFileName;
    private String memId;

    public MemberProfileImg() {
    }

    public MemberProfileImg(Integer imgIdx, String imgFileName, String memId) {
        this.imgIdx = imgIdx;
        this.imgFileName = imgFileName;
        this.memId = memId;
    }

    public Integer getImgIdx() {
        return imgIdx;
    }

    public void setImgIdx(Integer imgIdx) {
        this.imgIdx = imgIdx;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }
}
