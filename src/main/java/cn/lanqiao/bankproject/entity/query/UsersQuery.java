package cn.lanqiao.bankproject.entity.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersQuery {
    private long userId;
    private String name;
    private String accountNo;
    private String phone;
    private String registerTime;
    private double balance;
    private long status;
    private java.sql.Timestamp createTime;
    private java.sql.Timestamp updateTime;



    //
    // @Data
    // public class UsersQuery {
    //     private Integer pageNum = 1;
    //     private Integer pageSize = 10;
    //     private Integer offset;
    //     private String searchText;
    //     private Integer status;
    // }
}
