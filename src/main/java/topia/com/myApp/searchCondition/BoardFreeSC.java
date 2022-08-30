package topia.com.myApp.searchCondition;

import lombok.Data;

@Data
public class BoardFreeSC {
    private String keyword;

    public BoardFreeSC(String keyword) {
        this.keyword = keyword;
    }

    public BoardFreeSC() {
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
