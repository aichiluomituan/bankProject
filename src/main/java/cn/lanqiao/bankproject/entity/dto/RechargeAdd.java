package cn.lanqiao.bankproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeAdd {
    private String bank_card;
    private Integer bank_balance;
}
