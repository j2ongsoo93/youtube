package topia.com.myApp.searchCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class YtbSearchCondition {
    private String keyword;
    private String dataSize;
    private String pageNo;
    private String memId;

    public YtbSearchCondition() {
    }

    public YtbSearchCondition(String keyword, String dataSize, String pageNo, String memId) {
        this.keyword = keyword;
        this.dataSize = dataSize;
        this.pageNo = pageNo;
        this.memId = memId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }
}
