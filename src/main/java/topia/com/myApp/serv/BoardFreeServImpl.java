package topia.com.myApp.serv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import topia.com.myApp.dao.AbstractDAO;
import topia.com.myApp.entity.BoardFree;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BoardFreeServImpl implements BoardFreeServ{

    @Autowired
    private AbstractDAO dao;

    //insert, update
    @Override
    @Transactional
    public int save(HashMap<String, Object> inputData) {
        int re = 0;
        if(inputData.get("boardIdx") == null){
            re = (Integer)dao.insert("insertBoardFree", inputData);
        }else{
            re = (Integer)dao.update("updateBoardFree", inputData);
        }
        return re;
    }

    //selectList
    @Override
    public ArrayList<BoardFree> boardFreeList(HashMap<String, Object> reqMap) {
        ArrayList<BoardFree> list = (ArrayList<BoardFree>) dao.selectList("listBoardFree", reqMap);
        HashMap<String, Object> resMap = new HashMap<String, Object>();
        resMap.put("list", list);
        return list;
    }

    //selectOne
    @Override
    public HashMap<String, Object> boardFreeOne(int boardIdx) {
        HashMap<String, Object> resMap  = (HashMap<String, Object>) dao.selectOne("detailBoardFree", boardIdx);
        return resMap;
    }

    //delete
    @Override
    @Transactional
    public int delete(int boardIdx) {
        return (Integer)dao.delete("deleteBoardFree", boardIdx);
    }
}
