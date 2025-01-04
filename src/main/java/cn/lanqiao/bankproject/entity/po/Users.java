package cn.lanqiao.bankproject.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
        private Long Id;
        private String username;
        private String bank_card;
        private String phone;
        private String address;
        private Integer status;
}
