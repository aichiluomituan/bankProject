package cn.lanqiao.bankproject.entity.edit;

import lombok.Data;

/**
 * Description: AdTransaction修改实体类
 *
 * @author KaMiGui
 * @date 2025/1/5 上午12:55
 */
@Data
public class AdTransactionEdit {
    //交易编号
    private Integer id;
    //交易状态
    private String tradeState;
}
