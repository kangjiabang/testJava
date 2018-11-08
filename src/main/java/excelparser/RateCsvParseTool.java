package excelparser;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class RateCsvParseTool {

    //excel表格费率头固定格式
    private static List<String> rateHeader = ImmutableList.of("贷种名称", "贷种编号", "档次", "贷款利息", "服务费", "手续费", "逾期费率(日)", "综合费率");
    private String mFilePath;

    void setFilePath(String filePath) {
        mFilePath = filePath;
    }


    public static List<RateConfigVOCsv> parseCSVReader(String mFilePath) throws IOException {

        try {
            List<RateConfigVOCsv> beans = new CsvToBeanBuilder(new InputStreamReader(new FileInputStream(mFilePath),"gbk")
            ) .withType(RateConfigVOCsv.class).build().parse();
            return beans;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        /*try {
            System.out.println(parseCSVReader("/Users/zeqi/Documents/work/H5费率.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            RateCsvParseTool tool = new RateCsvParseTool();
            CSVReader reader = tool.initCSVReader(new FileInputStream("/Users/zeqi/Documents/work/秒贷H5费率.csv"));
            List<RateConfigVO> rateConfigVOList = Lists.newArrayList();
            tool.parseCSVReader(reader,rateConfigVOList);
            System.out.println(rateConfigVOList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  CSVReader initCSVReader(FileInputStream is) throws IOException {

        CSVReader reader = new CSVReader(new InputStreamReader(is,"GBK"));

        return reader;
    }

    public  void parseCSVReader(CSVReader reader, List<RateConfigVO> rateConfigVOList) {

        try {
            String [] nextLine;
            int count = 0;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line

                if (count == 0) {
                    validateRateHeader(nextLine);
                } else {
                    parseRowAndFillData(nextLine, rateConfigVOList);
                }
                count++;

            }
        } catch (IOException e) {

        }

    }

    /**
     * 验证excel表格头格式
     *
     */
    private  void validateRateHeader(String[] params) {
        List<String> rst = parseRow(params);
        if (!rateHeader.equals(rst)) {
            throw new RuntimeException("费率格式不正确，请重新编辑excel");
        }

    }

    private  void parseRowAndFillData(String[] params, List<RateConfigVO> rateConfigVOList) {

        int index = 0;
        RateConfigVO rateConfigVO = new RateConfigVO();
        for (int i=0;i< params.length;i++) {
            String cellValue = params[i];
            if (StringUtils.isEmpty(cellValue)) {
                break;
            }
            switch (index) {
                case 0:
                    rateConfigVO.setLoanName(cellValue);
                    break;
                case 1:
                    rateConfigVO.setLoanKey(cellValue);
                    break;
                case 2:
                    rateConfigVO.setRateLevel(cellValue);
                    break;
                case 3:
                    rateConfigVO.setInterestRate(trimPercentage(cellValue));
                    break;
                case 4:
                    rateConfigVO.setServiceRate(trimPercentage(cellValue));
                    break;
                case 5:
                    rateConfigVO.setFeeRate(trimPercentage(cellValue));
                    break;
                case 6:
                    rateConfigVO.setOverdueRate(trimPercentage(cellValue));
                    break;
                case 7:
                    rateConfigVO.setRate(trimPercentage(cellValue));
                    break;
            }
            index++;

        }
        if (StringUtils.isNotEmpty(rateConfigVO.getLoanName())) {
            rateConfigVOList.add(rateConfigVO);
        }

    }

    /**
     * 将0.15% 转换成 0.15
     *
     * @param cellValue
     * @return
     */
    private String trimPercentage(String cellValue) {
        if (StringUtils.isEmpty(cellValue)) {
            return "";
        }
        return cellValue.substring(0, cellValue.length() - 1);
    }

    private List<String> parseRow(String[] params) {
        List<String> rst = new ArrayList<>();

        for (int i=0;i< params.length;i++) {
            if (StringUtils.isEmpty(params[i])) {
                break;
            }
            rst.add(params[i]);
        }
        return rst;
    }

    public String getCellValue(Cell cell) {
        String cellValue = "";
        // 以下是判断数据的类型
        switch (cell.getCellTypeEnum()) {
            case NUMERIC: // 数字
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            case STRING: // 字符串
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                cellValue = cell.getBooleanCellValue() + "";
                break;
            case FORMULA: // 公式
                cellValue = cell.getNumericCellValue() + "";
                break;
            case BLANK: // 空值
                cellValue = "";
                break;
            case ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

}
