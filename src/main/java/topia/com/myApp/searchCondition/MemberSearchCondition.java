package topia.com.myApp.searchCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSearchCondition {
    private String searchCol;
    private String keyword;
    private Integer pageSize ;
    private Integer pageNo;
}
