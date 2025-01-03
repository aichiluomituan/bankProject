package cn.lanqiao.bankproject.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transferrecord {

    private long id;
    private String toAccount;
    private String toName;
    private double amount;
    private String notes;


}