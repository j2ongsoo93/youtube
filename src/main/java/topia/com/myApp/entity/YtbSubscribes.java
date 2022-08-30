package topia.com.myApp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class YtbSubscribes {
    private Integer subIdx;
    private String memId;
    private String ytbChannelName;

    public YtbSubscribes() {
    }

    public YtbSubscribes(Integer subIdx, String memId, String ytbChannelName) {
        this.subIdx = subIdx;
        this.memId = memId;
        this.ytbChannelName = ytbChannelName;
    }

    public Integer getSubIdx() {
        return subIdx;
    }

    public void setSubIdx(Integer subIdx) {
        this.subIdx = subIdx;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getYtbChannelName() {
        return ytbChannelName;
    }

    public void setYtbChannelName(String ytbChannelName) {
        this.ytbChannelName = ytbChannelName;
    }
}
