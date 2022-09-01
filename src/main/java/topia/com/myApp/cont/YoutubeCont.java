package topia.com.myApp.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import topia.com.myApp.crawler.YoutubeMain;
import topia.com.myApp.dto.BoardYoutubeDTO;
import topia.com.myApp.entity.BoardYoutube;
import topia.com.myApp.entity.YtbReply;
import topia.com.myApp.entity.YtbReplyReply;
import topia.com.myApp.entity.YtbSubscribes;
import topia.com.myApp.searchCondition.YtbSearchCondition;
import topia.com.myApp.serv.YoutubeServ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class YoutubeCont {
    @Autowired
    private YoutubeServ ys;

    //메인 화면 영상 리스트 출력
    @RequestMapping(value = "/youtube/list/main", method = RequestMethod.POST)
    @ResponseBody
    public List<BoardYoutube> listMain(@RequestBody YtbSearchCondition condition){
        List<BoardYoutube> list = (List<BoardYoutube>)ys.listMain(condition);
        return list;
    }

    //내가 등록한 영상조회
    @RequestMapping(value = ("/youtube/myPage/myVideos"), method = RequestMethod.POST)
    @ResponseBody
    public List<BoardYoutube> myVideos(@RequestBody YtbSearchCondition condition){
        return ys.listMain(condition);
    }

    //메인화면 구독리스트 출력

    //재생 화면 영상 출력
    @RequestMapping(value = "/youtube/play/{ytbIdx}")
    public String detailVideo(@PathVariable int ytbIdx, Model model){
        model.addAttribute("y", ys.detailVideo(ytbIdx));
        return "youtubePlay";
    }
    
    //재생 화면 추천 영상 출력
    @RequestMapping(value = "/youtube/list/recommend", method = RequestMethod.POST)
    @ResponseBody
    public List<BoardYoutube> recommendedVideo(@RequestBody YtbSearchCondition condition){
        List<BoardYoutube> list = (List<BoardYoutube>)ys.listMain(condition);
        return list;
    }

    //재생화면 댓글 출력

    //영상 등록
    @RequestMapping(value = "/youtube/reg", method = RequestMethod.POST)
    public String insertVideo(BoardYoutubeDTO dto, Model model){
        int re = -1;
        System.out.println(dto);

        HashMap<String, Object> inputData = new HashMap<>();

        inputData.put("ytbUrl", dto.getYtbUrl());
        inputData.put("memId", dto.getMemId());

        ys.insert(inputData);

//        //유튜브 메인 크롤링하여 샘플데이터 저장
//        YoutubeMain youtubeMain = new YoutubeMain();
//        ArrayList<Object> arr = youtubeMain.crawlYoutube("https://www.youtube.com/");
//        for(int i=0;i<arr.size();i++){
//            inputData.put("ytbUrl", arr.get(i));
//            ys.insert(inputData);
//        }
        return "/myPage";
    }
    
    //채널 구독
    @RequestMapping(value="/youtube/subsChannel", method = RequestMethod.POST)
    @ResponseBody
    public int subsChannel(@RequestBody YtbSubscribes subs){
        HashMap<String, Object> inputData = new HashMap<>();
        inputData.put("memId", subs.getMemId());
        inputData.put("ytbChannelName", subs.getYtbChannelName());
        ys.subsChannel(inputData);
        return 1;
    }

    //구독 취소
    @RequestMapping(value="/youtube/cancelSubs", method = RequestMethod.POST)
    @ResponseBody
    public int cancelSubs(@RequestBody YtbSubscribes subs){
        HashMap<String, Object> inputData = new HashMap<>();
        inputData.put("memId", subs.getMemId());
        inputData.put("ytbChannelName", subs.getYtbChannelName());
        ys.cancelSubs(inputData);
        return 1;
    }

    //채널 구독 여부 조회
    @RequestMapping(value="/youtube/isSubscribed", method = RequestMethod.POST)
    @ResponseBody
    public boolean isSubscribed(@RequestBody YtbSubscribes subs){
        boolean re;
        HashMap<String, Object> inputData = new HashMap<>();
        inputData.put("memId", subs.getMemId());
        inputData.put("ytbChannelName", subs.getYtbChannelName());
        re = ys.isSubscribed(inputData);
        return re;
    }

    //구독채널 조회
    @RequestMapping(value="/youtube/subsList/{memId}", method = RequestMethod.GET)
    @ResponseBody
    public List<BoardYoutube> listSubs(@PathVariable String memId){
        List<BoardYoutube> list = ys.listSubs(memId);
        return list;
    }

    //영상 삭제
    @RequestMapping(value = "/youtube/myPage/deleteVideo", method = RequestMethod.POST)
    @ResponseBody
    public void deleteVideo(@RequestBody String[] deleteList){
        System.out.println(deleteList);
        ys.deleteVideo(deleteList);
    }

    //댓글 조회
    @RequestMapping(value = "/youtube/play/listRe/{ytbIdx}", method = RequestMethod.GET)
    @ResponseBody
    public List<YtbReply> listRe(@PathVariable Integer ytbIdx){
        return ys.listRe(ytbIdx);
    }

    //대댓글 조회
    @RequestMapping(value = "/youtube/play/listReRe/{reIdx}", method = RequestMethod.GET)
    @ResponseBody
    public List<YtbReplyReply> listReRe(@PathVariable Integer reIdx){
        return ys.listReRe(reIdx);
    }

    //댓글 등록
    @RequestMapping(value = "/youtube/play/regRe", method = RequestMethod.POST)
    @ResponseBody
    public int regRe(@RequestBody YtbReply re){
        System.out.println(re);
        return ys.regRe(re);
    }

    //대댓글 등록
    @RequestMapping(value = "/youtube/play/regReRe", method = RequestMethod.POST)
    @ResponseBody
    public int regReRe(@RequestBody YtbReplyReply rere){
        System.out.println(rere);
        return ys.regReRe(rere);
    }

    //댓글 수정
    @RequestMapping(value = "/youtube/play/updateRe", method = RequestMethod.POST)
    @ResponseBody
    public int updateRe(@RequestBody YtbReply re){
        System.out.println(re);
        return ys.updateRe(re);
    }

    //대댓글 수정
    @RequestMapping(value = "/youtube/play/updateReRe", method = RequestMethod.POST)
    @ResponseBody
    public int updateReRe(@RequestBody YtbReplyReply rere){
        System.out.println(rere);
        return ys.updateReRe(rere);
    }

    //댓글 삭제
    @RequestMapping(value = "youtube/play/deleteRe/{reIdx}", method = RequestMethod.POST)
    @ResponseBody
    public int deleteRe(@PathVariable int reIdx){
        return ys.deleteRe(reIdx);
    }

    //댓글 삭제
    @RequestMapping(value = "youtube/play/deleteReRe/{rereIdx}", method = RequestMethod.POST)
    @ResponseBody
    public int deleteReRe(@PathVariable int rereIdx){
        return ys.deleteReRe(rereIdx);
    }
}
