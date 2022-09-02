package topia.com.myApp.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import topia.com.myApp.dto.MemberDTO;
import topia.com.myApp.searchCondition.MemberSearchCondition;

import java.util.List;

class AbstractDAOTest {
    AbstractDAO dao = new AbstractDAO();

    @Test
    public void searchMember(){
        MemberSearchCondition condition = new MemberSearchCondition();
        List<MemberDTO> list = dao.selectList("searchMember", condition);
    }
}
