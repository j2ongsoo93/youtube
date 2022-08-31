package topia.com.myApp;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import topia.com.myApp.dto.LoginInfoDTO;
import topia.com.myApp.serv.LoginServ;

import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private LoginServ ls;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, Principal principal, HttpSession session) {
		LoginInfoDTO dto = new LoginInfoDTO();
		if(principal != null){
			dto = ls.getInfo(principal.getName());
		}
		session.setAttribute("l", dto);
		return "youtubeHome";
	}

}
