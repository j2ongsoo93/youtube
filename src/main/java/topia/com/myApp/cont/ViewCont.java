package topia.com.myApp.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import topia.com.myApp.dto.LoginInfoDTO;
import topia.com.myApp.serv.LoginServ;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class ViewCont {
    @Autowired
    private LoginServ ls;

    //홈화면
    @RequestMapping(value = "/youtube/home", method = RequestMethod.GET)
    public String toMain(Model model, Principal principal, HttpSession session) {
        LoginInfoDTO dto = new LoginInfoDTO();
        if(principal != null){
            dto = ls.getInfo(principal.getName());
        }
        session.setAttribute("l", dto);
        return "youtubeHome";
    }

    //로그인 페이지 이동
    @RequestMapping(value = "/member/login")
    public String loginPage(Model model){
        return "login";
    }

    //로그인 에러페이지 이동
    @RequestMapping(value = "/member/login/error")
    public String loginError(Model model){
        return "loginError";
    }

    //회원가입 페이지 이동
    @RequestMapping(value = "/member/sign-up")
    public String signInPage(Model model){
        return "sign-up";
    }

    //마이페이지
    @RequestMapping(value = "/youtube/myPage")
    public String myPage(Model model){
        return "myPage";
    }

    //영상 등록 페이지
    @RequestMapping(value = "/youtube/myPage/reg")
    public String regVideo(Model model){
        return "regVideo";
    }

    //회원 관리 페이지
    @RequestMapping(value = "/youtube/member/admin")
    public String memberAdmin(Model model){
        return "memberAdmin";
    }
}
