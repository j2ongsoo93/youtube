package topia.com.myApp.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ExcelUtil {
    private int rowNum = 0;

    public void createExcelFile(List<Map<String,Object>> list, String path) throws FileNotFoundException, IOException {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("회원정보");

        rowNum = 0;

        creatExcel(sheet, list);

        FileOutputStream fos = new FileOutputStream(new File(path));
        workbook.write(fos);
        workbook.close();
    }

    public void createExcelResponse(List<Map<String,Object>> list, String fileName, HttpServletResponse response) throws IOException{
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("회원정보");

        rowNum = 0;

        creatExcel(sheet, list);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", fileName));

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    public void creatExcel(Sheet sheet, List<Map<String, Object>> list){
        //데이터 조회 하여 1개의 행으로 만듦
        for(Map<String, Object> data: list){
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;

            for (String key : data.keySet()){
                Cell cell = row.createCell(cellNum++);

                cell.setCellValue(data.get(key).toString());
            }
        }
    }
}
