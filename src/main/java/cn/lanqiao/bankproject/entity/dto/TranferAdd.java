package cn.lanqiao.bankproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranferAdd {
    private long id;
    private String payee;
    private String bankcard;
    private double money;
    private String remark;
    private String payer;
    private String fromCard;
}
