package excelparser;

import com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RateExcelParseTool {

    //excel表格费率头固定格式
    private List<String> rateHeader = ImmutableList.of("贷种名称", "贷种编号", "档次", "贷款利息", "服务费", "手续费", "逾期费率(日)", "综合费率");
    private String mFilePath;

    private DataFormatter formatter;
    private FormulaEvaluator evaluator;


    void setFilePath(String filePath) {
        mFilePath = filePath;
    }

    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";

    Workbook initWorkBook() throws IOException {
        File file = new File(mFilePath);
        InputStream is = new FileInputStream(file);

        Workbook workbook = null;
        if (mFilePath.endsWith(SUFFIX_2003)) {
            workbook = new HSSFWorkbook(is);
        } else if (mFilePath.endsWith(SUFFIX_2007)) {
            workbook = new XSSFWorkbook(is);
        }
        this.evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        this.formatter = new DataFormatter(true);

        return workbook;
    }

    public Workbook initWorkBook(InputStream is, String originalFilename) throws IOException {

        Workbook workbook = null;
        if (originalFilename.endsWith(SUFFIX_2003)) {
            workbook = new HSSFWorkbook(is);
        } else if (originalFilename.endsWith(SUFFIX_2007)) {
            workbook = new XSSFWorkbook(is);
        }

        this.evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        this.formatter = new DataFormatter(true);
        return workbook;
    }

    public void parseWorkbook(Workbook workbook, List<RateConfigVO> rateConfigVOList) {
        int numOfSheet = workbook.getNumberOfSheets();

        for (int i = 0; i < numOfSheet; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            parseSheet(sheet, rateConfigVOList);
        }
    }

    private void parseSheet(Sheet sheet, List<RateConfigVO> rateConfigVOList) {
        Row row;

        int count = 0;

        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            row = iterator.next();

            if (count == 0) {
                //TODO
                //validateRateHeader(row);
            }
            else if (count == 1) {
                validateRateHeader(row);
            } else {
                parseRowAndFillData(row, rateConfigVOList);
            }

            ++count;
        }
    }

    /**
     * 验证excel表格头格式
     *
     * @param row
     */
    private void validateRateHeader(Row row) {
        List<String> rst = parseRow(row);
        if (!rateHeader.equals(rst)) {
            throw new RuntimeException("费率格式不正确，请重新编辑excel");
        }

    }

    private void parseRowAndFillData(Row row, List<RateConfigVO> rateConfigVOList) {
        Iterator<Cell> iterator = row.iterator();
        int index = 0;
        RateConfigVO rateConfigVO = new RateConfigVO();
        while (iterator.hasNext()) {
            Cell cell = iterator.next();
            String cellValue = this.getCellValueStandard(cell);
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
            System.out.println(rateConfigVO);
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

    private List<String> parseRow(Row row) {
        List<String> rst = new ArrayList<>();

        Cell cell;

        Iterator<Cell> iterator = row.iterator();
        while (iterator.hasNext()) {
            cell = iterator.next();
            rst.add(this.getCellValue(cell));
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


    public String getCellValueStandard(Cell cell) {

        if(cell.getCellType() != CellType.FORMULA.getCode()) {
            return this.formatter.formatCellValue(cell);
        }
        else {
            return this.formatter.formatCellValue(cell, this.evaluator);
        }
    }

}
