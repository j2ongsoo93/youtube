package topia.com.myApp.serv;

import topia.com.myApp.dto.BoardFreeDTO;
import topia.com.myApp.entity.BoardFree;
import topia.com.myApp.searchCondition.BoardFreeSC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface BoardFreeServ {
    //insert, update
    int save(HashMap<String, Object> inputData);

    //selectList
    ArrayList<BoardFree> boardFreeList(HashMap<String, Object> reqMap);

    //selectOne
    HashMap<String, Object> boardFreeOne(int boardIdx);

    //delete
    int delete(int boardIdx);

}
