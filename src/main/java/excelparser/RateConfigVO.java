/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excelparser;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zeqi
 * @Date: Created in 10:54 7/3/18
 * @Description:
 * 费率配置
 */
@Data
public class RateConfigVO implements Serializable {

    //贷种编号
    private String loanKey;
    //贷种名称
    private String loanName;

    //费率等级
    private String rateLevel;
    //综合费率
    private String rate;
    //贷款利息率
    private String interestRate;
    //服务费率
    private String serviceRate;

    //手续费率
    private String feeRate;

    //逾期费率
    private String overdueRate;

}
