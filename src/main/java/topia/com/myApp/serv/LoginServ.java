package topia.com.myApp.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import topia.com.myApp.dao.AbstractDAO;
import topia.com.myApp.dto.LoginInfoDTO;

@Service("LoginServ")
public class LoginServ {

    @Autowired
    private AbstractDAO dao;

    public LoginInfoDTO getInfo(String memId){
        return (LoginInfoDTO) dao.selectOne("getInfo", memId);
    }
}
