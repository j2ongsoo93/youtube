package topia.com.myApp.cont;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import topia.com.myApp.dto.MemberDTO;
import topia.com.myApp.excel.ExcelUtil;
import topia.com.myApp.searchCondition.MemberSearchCondition;
import topia.com.myApp.serv.MemberServImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ExcelCont {
    @Autowired
    private MemberServImpl ms;

    @Autowired
    private ExcelUtil excelUtil;

    @RequestMapping(value = "/member/search/downloadExcel")
    public void downloadMemberExcel(HttpServletResponse response) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("회원정보");
        int rowNum = 0;

        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("가입일");
        headerRow.createCell(3).setCellValue("수정일");
        headerRow.createCell(4).setCellValue("가입ID");
        headerRow.createCell(5).setCellValue("수정ID");
        headerRow.createCell(6).setCellValue("가입IP");
        headerRow.createCell(7).setCellValue("수정IP");
        headerRow.createCell(8).setCellValue("이메일");
        headerRow.createCell(9).setCellValue("상태");
        headerRow.createCell(10).setCellValue("권한");

        List<MemberDTO> list = ms.searchMemberForExcel();
        for(MemberDTO m: list){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(m.getMemId());
            row.createCell(1).setCellValue(m.getMemName());
            row.createCell(2).setCellValue(m.getRegistDate().toString());
            row.createCell(3).setCellValue(m.getUpdateDate().toString());
            row.createCell(4).setCellValue(m.getRegistId());
            row.createCell(5).setCellValue(m.getUpdateId());
            row.createCell(6).setCellValue(m.getRegistIp());
            row.createCell(7).setCellValue(m.getUpdateIp());
            row.createCell(8).setCellValue(m.getMemEmail());
            row.createCell(9).setCellValue(m.getEnabled());
            row.createCell(10).setCellValue(m.getAuthority());
        }

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=memberList.xls");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
