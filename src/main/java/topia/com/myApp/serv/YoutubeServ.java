package topia.com.myApp.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topia.com.myApp.crawler.YoutubeCrawler;
import topia.com.myApp.dao.AbstractDAO;
import topia.com.myApp.entity.BoardYoutube;
import topia.com.myApp.entity.YtbReply;
import topia.com.myApp.entity.YtbReplyReply;
import topia.com.myApp.entity.YtbSubscribes;
import topia.com.myApp.searchCondition.YtbSearchCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class YoutubeServ {
    @Autowired
    private AbstractDAO dao;
    @Autowired
    private YoutubeCrawler cw;

    // 영상 등록
    public int insert(HashMap<String, Object> inputData){
        int re = -1;

        HashMap<String, Object> youtubeInfo = cw.crawlYoutube((String) inputData.get("ytbUrl"));
        youtubeInfo.put("ytbUrl", inputData.get("ytbUrl"));
        youtubeInfo.put("memId", inputData.get("memId"));
        re = (Integer) dao.insert("insertYtb", youtubeInfo);

        return re;
    }

    // 영상 리스트 조회
    //내가 등록한 영상 조회
    public ArrayList<BoardYoutube> listMain(YtbSearchCondition condition){
        HashMap<String, Object> reqMap = new HashMap<>();

        Integer dataSize = Integer.parseInt(condition.getDataSize());
        Integer pageNo = Integer.parseInt(condition.getPageNo());

        int start = dataSize*pageNo-dataSize+1;
        int end = dataSize*pageNo;

        reqMap.put("keyword", condition.getKeyword());
        reqMap.put("memId", condition.getMemId());
        reqMap.put("start", String.valueOf(start));
        reqMap.put("end", String.valueOf(end));


        HashMap<String, Object> resMap = new HashMap<>();

        ArrayList<BoardYoutube> list = (ArrayList<BoardYoutube>) dao.selectList("listVideo", reqMap);
        resMap.put("list", list);
        return list;
    }

    // 영상 상세
    public BoardYoutube detailVideo(int ytbIdx){
        dao.update("plusHit", ytbIdx);
        return (BoardYoutube) dao.selectOne("detailVideo", ytbIdx);
    }

    //채널 구독
    public int subsChannel(HashMap<String, Object> inputData){
        int re = -1;
        re = (Integer) dao.insert("subsChannel", inputData);
        return re;
    }

    //채널 구독 취소
    public int cancelSubs(HashMap<String, Object> inputData){
        int re = -1;
        re = (Integer)dao.delete("cancelSubs", inputData);
        return re;
    }

    //채널 구독 여부 판별
    public boolean isSubscribed(HashMap<String, Object> inputData){
        boolean re = false;
        ArrayList<YtbSubscribes> list = (ArrayList<YtbSubscribes>) dao.selectList("isSubscribed", inputData);

        if(list.get(0).getYtbChannelName() != null){
            re = true;
        }
        return re;
    }

    //구독채널 리스트 조회
    public List<BoardYoutube> listSubs(String memId){
        List<BoardYoutube> list = new ArrayList<>();
        list = dao.selectList("subsList", memId);
        return list;
    }

    //영상삭제
    public void deleteVideo(String[] deleteList){
        for(int i = 0; i<deleteList.length;i++){
            dao.delete("deleteVideo", deleteList[i]);
        }
    }

    //영상 댓글 조회
    public List<YtbReply> listRe(Integer ytbIdx){
        List<YtbReply> list = new ArrayList<>();
        list = dao.selectList("listRe", ytbIdx);
        return list;
    }

    //영상 대댓글 조회
    public List<YtbReplyReply> listReRe(Integer reIdx){
        List<YtbReplyReply> list = new ArrayList<>();
        list = dao.selectList("listReRe", reIdx);
        return list;
    }

    //댓글 등록
    public int regRe(YtbReply re){
        return (Integer)dao.insert("insertRe", re);
    }

    //대댓글 등록
    public int regReRe(YtbReplyReply rere){
        return (Integer)dao.insert("insertReRe", rere);
    }

    //댓글 수정
    public int updateRe(YtbReply re){
        return (Integer)dao.update("updateRe", re);
    }

    //대댓글 수정
    public int updateReRe(YtbReplyReply rere){
        System.out.println(rere);
        return (Integer)dao.update("updateReRe", rere);
    }

    //댓글 삭제
    public int deleteRe(int reIdx){
        return (Integer)dao.delete("deleteRe", reIdx);
    }

    //대댓글 삭제
    public int deleteReRe(int rereIdx){
        return (Integer)dao.delete("deleteReRe", rereIdx);
    }
}
