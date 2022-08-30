package topia.com.myApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoginInfoDTO {
    private String mem_id;
    private String mem_name;
    private String authority;
    private String img_file_name;

    public LoginInfoDTO() {
    }

    public LoginInfoDTO(String mem_id, String mem_name, String authority, String img_file_name) {
        this.mem_id = mem_id;
        this.mem_name = mem_name;
        this.authority = authority;
        this.img_file_name = img_file_name;
    }

    public String getMem_id() {
        return mem_id;
    }

    public void setMem_id(String mem_id) {
        this.mem_id = mem_id;
    }

    public String getMem_name() {
        return mem_name;
    }

    public void setMem_name(String mem_name) {
        this.mem_name = mem_name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getImg_file_name() {
        return img_file_name;
    }

    public void setImg_file_name(String img_file_name) {
        this.img_file_name = img_file_name;
    }
}
