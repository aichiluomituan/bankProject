package cn.lanqiao.bankproject.entity.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 查询分页数据
 *
 * @author KaMiGui
 * @date 2025/1/3 下午5:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdTransactionQuery {
    private Integer id; //交易编码
    private String date; //交易时间
    private String tradeType; //交易类型
    private String money; //金额
    private String tradeState; //交易状态
    private String payer; // 付款方
    private String payee; // 收款方

    private String payerBankCard; // 付款方卡号
    private String payeeBankCard; // 收款方卡号
}
