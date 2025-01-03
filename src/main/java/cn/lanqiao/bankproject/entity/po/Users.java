package cn.lanqiao.bankproject.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
        private Long userId;
        private String name;
        private String accountNo;
        private String phone;
        private String registerTime;
        private String balance;
        private Integer status;
        private String createTime;
        private String updateTime;
}
