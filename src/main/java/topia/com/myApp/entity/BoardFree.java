package topia.com.myApp.entity;

import lombok.Data;

@Data
public class BoardFree {
    private Integer boardIdx;
    private String userId;
    private String userName;
    private String boardContent;

    public BoardFree() {
    }

    public BoardFree(Integer boardIdx, String userId, String userName, String boardContent) {
        this.boardIdx = boardIdx;
        this.userId = userId;
        this.userName = userName;
        this.boardContent = boardContent;
    }

    public Integer getBoardIdx() {
        return boardIdx;
    }

    public void setBoardIdx(Integer boardIdx) {
        this.boardIdx = boardIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }


}
