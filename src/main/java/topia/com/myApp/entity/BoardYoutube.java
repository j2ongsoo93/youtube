package topia.com.myApp.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardYoutube {
    private Integer ytbIdx;
    private String ytbTitle;
    private String ytbUrl;
    private String ytbInfo;
    private Date ytbRegDate;
    private Integer ytbHit;
    private Integer ytbLike;
    private String ytbChannelName;
    private String ytbChannelUrl;
    private String ytbThumbnail;
    private String memId;
    private String ytbChannelThumb;
    private String ytbEmbedUrl;
    private List<YtbReply> reList;

    public BoardYoutube() {
    }

    public BoardYoutube(Integer ytbIdx, String ytbTitle, String ytbUrl, String ytbInfo, Date ytbRegDate, Integer ytbHit, Integer ytbLike, String ytbChannelName, String ytbChannelUrl, String ytbThumbnail, String memId, String ytbChannelThumb, String ytbEmbedUrl, List<YtbReply> reList) {
        this.ytbIdx = ytbIdx;
        this.ytbTitle = ytbTitle;
        this.ytbUrl = ytbUrl;
        this.ytbInfo = ytbInfo;
        this.ytbRegDate = ytbRegDate;
        this.ytbHit = ytbHit;
        this.ytbLike = ytbLike;
        this.ytbChannelName = ytbChannelName;
        this.ytbChannelUrl = ytbChannelUrl;
        this.ytbThumbnail = ytbThumbnail;
        this.memId = memId;
        this.ytbChannelThumb = ytbChannelThumb;
        this.ytbEmbedUrl = ytbEmbedUrl;
        this.reList = reList;
    }

    public Integer getYtbIdx() {
        return ytbIdx;
    }

    public void setYtbIdx(Integer ytbIdx) {
        this.ytbIdx = ytbIdx;
    }

    public String getYtbTitle() {
        return ytbTitle;
    }

    public void setYtbTitle(String ytbTitle) {
        this.ytbTitle = ytbTitle;
    }

    public String getYtbUrl() {
        return ytbUrl;
    }

    public void setYtbUrl(String ytbUrl) {
        this.ytbUrl = ytbUrl;
    }

    public String getYtbInfo() {
        return ytbInfo;
    }

    public void setYtbInfo(String ytbInfo) {
        this.ytbInfo = ytbInfo;
    }

    public Date getYtbRegDate() {
        return ytbRegDate;
    }

    public void setYtbRegDate(Date ytbRegDate) {
        this.ytbRegDate = ytbRegDate;
    }

    public Integer getYtbHit() {
        return ytbHit;
    }

    public void setYtbHit(Integer ytbHit) {
        this.ytbHit = ytbHit;
    }

    public Integer getYtbLike() {
        return ytbLike;
    }

    public void setYtbLike(Integer ytbLike) {
        this.ytbLike = ytbLike;
    }

    public String getYtbChannelName() {
        return ytbChannelName;
    }

    public void setYtbChannelName(String ytbChannelName) {
        this.ytbChannelName = ytbChannelName;
    }

    public String getYtbChannelUrl() {
        return ytbChannelUrl;
    }

    public void setYtbChannelUrl(String ytbChannelUrl) {
        this.ytbChannelUrl = ytbChannelUrl;
    }

    public String getYtbThumbnail() {
        return ytbThumbnail;
    }

    public void setYtbThumbnail(String ytbThumbnail) {
        this.ytbThumbnail = ytbThumbnail;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getYtbChannelThumb() {
        return ytbChannelThumb;
    }

    public void setYtbChannelThumb(String ytbChannelThumb) {
        this.ytbChannelThumb = ytbChannelThumb;
    }

    public String getYtbEmbedUrl() {
        return ytbEmbedUrl;
    }

    public void setYtbEmbedUrl(String ytbEmbedUrl) {
        this.ytbEmbedUrl = ytbEmbedUrl;
    }

    public List<YtbReply> getReList() {
        return reList;
    }

    public void setReList(List<YtbReply> reList) {
        this.reList = reList;
    }
}
