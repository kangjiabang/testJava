package excelparser;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author：zeqi
 * @Date: Created in 14:35 2/5/18.
 * @Description:
 */
public class DealExcels {

    public static void main(String[] args) {
        String buffer = "";
        try {
            String fileName = "/Users/zeqi/Documents/work/excel_normal.xlsx";
            File file = new File(fileName);
            // 设置读文件编码
            WorkbookSettings setEncode = new WorkbookSettings();
            setEncode.setEncoding("utf-8");
            // 从文件流中获取Excel工作区对象（WorkBook）
            Workbook wb = Workbook.getWorkbook(file,setEncode);
            Sheet sheet = wb.getSheet(0);

            for (int i = 0; i < sheet.getRows(); i++) {
                for (int j = 0; j < 11; j++) {
                    Cell cell = sheet.getCell(j, i);
                    buffer += cell.getContents().replaceAll("\n", " ")+",";
                }
                buffer = buffer.substring(0, buffer.lastIndexOf(",")).toString();
                buffer += "\n";
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //write the string into the file
        String savePath = "/Users/zeqi/Documents/work/datas.csv";
        File saveCSV = new File(savePath);
        try {
            if(!saveCSV.exists()) {

                saveCSV.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveCSV));
            writer.write(buffer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
