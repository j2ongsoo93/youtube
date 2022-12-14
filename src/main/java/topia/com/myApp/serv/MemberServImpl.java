package topia.com.myApp.serv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import topia.com.myApp.cont.LoginCont;
import topia.com.myApp.dao.AbstractDAO;
import topia.com.myApp.dto.MemberDTO;
import topia.com.myApp.excel.ExcelUtil;
import topia.com.myApp.searchCondition.MemberSearchCondition;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServImpl implements MemberServ {

    private static final Logger logger = LoggerFactory.getLogger(LoginCont.class);
    @Autowired
    private AbstractDAO dao;

    @Autowired
    private ExcelUtil excelUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    //insert, update
    @Override
    public int insert(MemberDTO dto, Principal principal) {
        int re = -1;
        HashMap<String, Object> inputData = new HashMap<String, Object>();

        try {
        inputData.put("memId", dto.getMemId());
        inputData.put("memName", dto.getMemName());
        inputData.put("password", dto.getPassword());
        inputData.put("memProfile", dto.getMemProfile());
        inputData.put("memEmail", dto.getMemEmail());
        inputData.put("registDate", null);
        inputData.put("registId", dto.getMemId());
        inputData.put("registIp", InetAddress.getLocalHost().toString().split("/")[1]);
        logger.info(String.valueOf(InetAddress.getLocalHost()));
        inputData.put("enabled", 1);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        re = (Integer) dao.insert("insertMember",inputData);
        dao.insert("insertMemberAuth", inputData);

        return re;
    }

    @Override
    public int update(MemberDTO dto, Principal principal) {
        int re = -1;
        HashMap<String, Object> inputData = new HashMap<String, Object>();
        try {
            inputData.put("memId", dto.getMemId());
            inputData.put("password", dto.getPassword());
            inputData.put("memEmail", dto.getMemEmail());
            inputData.put("updateDate", null);
            inputData.put("updateId", principal.getName());
            inputData.put("updateIp", InetAddress.getLocalHost().toString().split("/")[1]);
            inputData.put("enabled", dto.getEnabled());
            inputData.put("authority", dto.getAuthority());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        re = (Integer) dao.update("updateMember",inputData);
        if(re == 1){
            dao.update("updateMemberAuth",inputData);
        }

        return re;
    }

    @Override
    public ArrayList<MemberDTO> selectList(HashMap<String, Object> reqMap) {
        return null;
    }

    @Override
    public MemberDTO selectOne(String memId) {
        HashMap<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("memId", memId);
        MemberDTO dto = (MemberDTO)dao.selectOne("detailMember", reqMap);
        return dto;
    }

    @Override
    public int delete(int Idx) {
        return 0;
    }
    
    //????????? ?????????, ????????? insert
    public int uploadProfileImg(MultipartFile multi, String memId){
        logger.info("=======================================================");
        logger.info("Upload File Name : " + multi.getOriginalFilename());
        logger.info("Upload File Size : " + multi.getSize());
        
        //????????? ?????? ?????????
        String uploadPath = "C:/GitHub/youtube/src/main/webapp/resources/upload/profileImg";
        String originalFileName = multi.getOriginalFilename();
        int idxOfDot = multi.getOriginalFilename().lastIndexOf(".");
        String extension = originalFileName.substring(idxOfDot+1);
        String saveFileName = memId+"_"+multi.getOriginalFilename();

        try {
            if(!multi.isEmpty()){
                File file = new File(uploadPath, saveFileName);
                multi.transferTo(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //DB??? ????????? ??????
        HashMap<String, Object> inputData = new HashMap<>();
        inputData.put("imgFileName", saveFileName);
        inputData.put("memId", memId);
        return (Integer) dao.insert("insertMemberProfileImg", inputData);
    }
    
    //????????? ?????????, ????????? update
    public int updateProfileImg(MultipartFile multi, String memId){
        logger.info("=======================================================");
        logger.info("Upload File Name : " + multi.getOriginalFilename());
        logger.info("Upload File Size : " + multi.getSize());

        //????????? ?????? ?????????
        String uploadPath = "C:/GitHub/youtube/src/main/webapp/resources/upload/profileImg";
        String originalFileName = multi.getOriginalFilename();
        int idxOfDot = multi.getOriginalFilename().lastIndexOf(".");
        String extension = originalFileName.substring(idxOfDot+1);
        String saveFileName = memId+"_"+multi.getOriginalFilename();

        try {
            if(!multi.isEmpty()){
                File file = new File(uploadPath, saveFileName);
                multi.transferTo(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //DB??? ????????? ??????
        HashMap<String, Object> inputData = new HashMap<>();
        inputData.put("imgFileName", saveFileName);
        inputData.put("memId", memId);
        return (Integer) dao.insert("updateMemberProfileImg", inputData);
    }
    
    //?????? ??????
    public List<MemberDTO> searchMember(MemberSearchCondition condition){
        return (ArrayList<MemberDTO>)dao.selectList("searchMember", condition);
    }

    //?????? ????????? ????????????
    public List<MemberDTO> searchMemberForExcel(){
        return (ArrayList<MemberDTO>)dao.selectList("searchMemberForExcel");
    }

    //????????????
    public MemberDTO findById(String memId){
        return (MemberDTO)dao.selectOne("findMemberById", memId);
    }
}
