/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelparser;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zeqi
 * @Date: Created in 10:54 7/3/18
 * @Description:
 * 费率配置
 */
@Data
public class RateConfigVOCsv implements Serializable {

    //贷种编号
    @CsvBindByName(column = "贷种编号", required = true)
    private String loanKey;
    //贷种名称
    @CsvBindByName(column = "贷种名称", required = true)
    private String loanName;

    //费率等级
    @CsvBindByName(column = "档次", required = true)
    private String rateLevel;
    //综合费率
    @CsvBindByName(column = "综合费率", required = true)
    private String rate;
    //贷款利息率
    @CsvBindByName(column = "贷款利息", required = true)
    private String interestRate;
    //服务费率
    @CsvBindByName(column = "服务费", required = true)
    private String serviceRate;

    //手续费率
    @CsvBindByName(column = "手续费", required = true)
    private String feeRate;

    //逾期费率
    @CsvBindByName(column = "逾期费率(日)", required = true)
    private String overdueRate;

}
