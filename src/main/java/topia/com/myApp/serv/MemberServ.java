package topia.com.myApp.serv;

import topia.com.myApp.dto.MemberDTO;
import topia.com.myApp.entity.BoardFree;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;

public interface MemberServ {
    //insert, update
    int insert(MemberDTO dto, Principal principal);
    int update(MemberDTO dto, Principal principal);

    //selectList
    ArrayList<MemberDTO> selectList(HashMap<String, Object> reqMap);

    //selectOne
   MemberDTO selectOne(String Idx);

    //delete
    int delete(int Idx);
}
