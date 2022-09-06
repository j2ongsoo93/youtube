package topia.com.myApp.cont;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import topia.com.myApp.dto.LoginInfoDTO;
import topia.com.myApp.dto.MemberDTO;
import topia.com.myApp.entity.Member;
import topia.com.myApp.searchCondition.MemberSearchCondition;
import topia.com.myApp.serv.LoginServ;
import topia.com.myApp.serv.MemberServImpl;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;


@Controller
public class LoginCont {
    private static final Logger logger = LoggerFactory.getLogger(LoginCont.class);

    @Autowired
    private MemberServImpl ms;

    @Autowired
    private LoginServ ls;

    //로그인 정보 호출
    @RequestMapping(value = "/member/loginInfo", method = RequestMethod.POST)
    @ResponseBody
    public LoginInfoDTO getLoginInf(@RequestBody String memId){
        logger.info(memId);
        return ls.getInfo(memId);
    }

    //회원가입시 아이디 중복 체크
    @RequestMapping("/member/sign-up/idCheck/{memId}")
    @ResponseBody
    public boolean idDuplicationCheck(@PathVariable String memId){
        boolean re = false;
        MemberDTO dto = ms.selectOne(memId);
        if(dto == null){
            re = true;
        }

        return re;
    }

    //회원가입
    @RequestMapping(value = "/member/insert", method = RequestMethod.POST)
    @ResponseBody
    public int insertMember(@RequestBody MemberDTO dto, Principal principal, Model model){
        logger.info(String.valueOf(dto));
        int re = ms.insert(dto, principal);
        return re;
    }

    //회원정보 수정
    @RequestMapping(value = "/member/update", method = RequestMethod.POST)
    @ResponseBody
    public int updateMember(@RequestBody MemberDTO dto, Principal principal, Model model){
        int re = -1;
        logger.info(String.valueOf(dto));
        re = ms.update(dto, principal);
        return re;
    }

    //회원 프로필 사진 업로드
    @RequestMapping(value = "/member/sign-up/upload/profileImg", method = RequestMethod.POST)
    @ResponseBody
    public int uploadProfileImg(@RequestParam("profileImg") MultipartFile multi, @RequestParam("memId") String memId){
        return ms.uploadProfileImg(multi, memId);
    }

    //회원 프로필 사진 수정
    @RequestMapping(value = "/member/sign-up/upload/profileImg/update", method = RequestMethod.POST)
    @ResponseBody
    public int updateProfileImg(@RequestParam("profileImg") MultipartFile multi, @RequestParam("memId") String memId){
        return ms.updateProfileImg(multi, memId);
    }

    //파일명 생성 메서드(안씀)
    private String genFileName(String originFileName, String extension){
        String fileName = "";
        Calendar calendar = Calendar.getInstance();
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += "_"+originFileName;
        fileName += extension;
        return fileName;
    }


    //회원 검색
    @RequestMapping(value = "/member/search", method = RequestMethod.POST)
    @ResponseBody
    public List<MemberDTO> searchMember(@RequestBody MemberSearchCondition condition){
        return ms.searchMember(condition);
    }

    //회원 단일조회
    @RequestMapping(value = "member/findById/{memId}", method = RequestMethod.POST)
    @ResponseBody
    public MemberDTO findById(@PathVariable String memId){
        logger.info(memId);
        return ms.findById(memId);
    }
}
