package cn.lanqiao.bankproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Withdrawdel {
    private String bankCard;
    private Integer bankBalance;
    private String trade_pwd;
    private String payer;
}
