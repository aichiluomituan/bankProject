package cn.lanqiao.bankproject.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranferAdd {
    private long id;
    private String toaccount;
    private String toname;
    private double amount;
    private String notes;
}
