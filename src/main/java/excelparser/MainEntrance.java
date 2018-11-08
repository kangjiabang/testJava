package excelparser;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainEntrance {
    public static void main(String[] args) {
        RateExcelParseTool excelParseTool = new RateExcelParseTool();

        excelParseTool.setFilePath("/Users/zeqi/Documents/work/秒贷H5费率.xlsx");

        try {
            Workbook workbook = excelParseTool.initWorkBook();

            List<RateConfigVO> outData = new ArrayList<>();

            if (workbook != null) {
                excelParseTool.parseWorkbook(workbook, outData);
            }


        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
