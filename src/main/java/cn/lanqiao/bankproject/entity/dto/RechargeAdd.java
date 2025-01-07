package cn.lanqiao.bankproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeAdd {
    private String bankCard;
    private Integer bankBalance;
    private String remark;
    private String payee;
}
