package topia.com.myApp.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import topia.com.myApp.dto.BoardFreeDTO;
import topia.com.myApp.entity.BoardFree;
import topia.com.myApp.searchCondition.BoardFreeSC;
import topia.com.myApp.serv.BoardFreeServImpl;

import java.util.HashMap;
import java.util.List;

@Controller
public class BoardFreeContImpl implements BoardFreeCont{

    @Autowired
    private BoardFreeServImpl bs;

    //게시판 등록, 수정
    @RequestMapping(value = "/boardFree/save", method = RequestMethod.POST)
    public String saveBoardFree(BoardFreeDTO dto, Model model) {
        HashMap<String, Object> inputData = new HashMap<String, Object>();
        inputData.put("boardIdx", dto.getBoardIdx());
        inputData.put("userId", dto.getUserId());
        inputData.put("userName", dto.getUserName());
        inputData.put("boardContent", dto.getBoardContent());
        bs.save(inputData);

        return "redirect:/boardFree/list";
    }

    //게시판 등록으로 이동
    @RequestMapping(value = "/boardFree/insert", method = RequestMethod.GET)
    public String insert(Model model){
        return "board/insert";
    }

    //게시판 수정으로 이동
    @RequestMapping(value = "/boardFree/update/{boardIdx}", method = RequestMethod.GET)
    public String insert(@PathVariable Integer boardIdx, Model model){
        model.addAttribute("b", bs.boardFreeOne(boardIdx));
        return "board/insert";
    }

    //리스트 페이지 조회
    @RequestMapping(value="/boardFree/list", method = RequestMethod.POST)
    public String listBoardFree(BoardFreeSC sc, Model model){
        HashMap<String, Object> reqMap = new HashMap<String, Object>();
        if(!sc.getKeyword().equals("")){
            String[] keyword = {sc.getKeyword()};
            reqMap.put("keyword", keyword);
        }
        System.out.println(sc.getKeyword());

//        HashMap<String, Object> resMap = bs.boardFreeList(reqMap);
        List<BoardFree> list = (List<BoardFree>) bs.boardFreeList(reqMap);
        model.addAttribute("list", list);
        return "board/home";
    }

//    //리스트 페이지로 이동
//    @RequestMapping(value="/boardFree/list", method = RequestMethod.GET)
//    public String listBoardFree(Model model){
//        HashMap<String, Object> reqMap = new HashMap<String, Object>();
//        HashMap<String, Object> resMap = bs.boardFreeList(reqMap);
//        model.addAttribute("list", resMap.get("list"));
//        return "home";
//    }

    //게시판 상세내용 조회
    @RequestMapping(value="/boardFree/{boardIdx}", method = RequestMethod.GET)
    public String detailBoardFree(@PathVariable int boardIdx, Model model){
        model.addAttribute("b", bs.boardFreeOne(boardIdx));
        return "board/detail";
    }

    //게시판 내용 삭제
    @RequestMapping(value = "/boardFree/delete/{boardIdx}", method = RequestMethod.GET)
    public String deleteBoard(@PathVariable int boardIdx, Model model){
        bs.delete(boardIdx);
        return "redirect:/boardFree/list";
    }
    
    //AJAX용 method
    @Override
    public int save(BoardFreeDTO dto) {
        return 0;
    }

    @Override
    public List<BoardFreeDTO> listBoardFree(BoardFreeSC sc) {
        return null;
    }

    @Override
    public BoardFreeDTO detailBoardFree(int boardIdx) {
        return null;
    }

    @Override
    public int delete(int boardIdx) {
        return 0;
    }
}
