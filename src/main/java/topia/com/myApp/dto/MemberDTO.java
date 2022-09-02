package topia.com.myApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private String memId;
    private String memName;
    private String password;
    private Date registDate;
    private Date updateDate;
    private String registId;
    private String updateId;
    private String registIp;
    private String updateIp;
    private String memProfile;
    private String memEmail;
    private Integer enabled;
    private String authority;
    private String imgFileName;
}
