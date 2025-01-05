package cn.lanqiao.bankproject.entity.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersQuery {
    private Long Id;
    private String username;
    private String bank_card;
    private String phone;
    private String address;
    private Integer status;
    private String balance;
    // 分页参数
    private Integer pageNum = 1;
    private Integer pageSize = 8;
}