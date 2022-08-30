package topia.com.myApp.cont;

import topia.com.myApp.dto.BoardFreeDTO;
import topia.com.myApp.searchCondition.BoardFreeSC;

import java.util.List;

public interface BoardFreeCont {
    int save(BoardFreeDTO dto);
    List<BoardFreeDTO> listBoardFree(BoardFreeSC sc);
    BoardFreeDTO detailBoardFree(int boardIdx);
    int delete(int boardIdx);
}
