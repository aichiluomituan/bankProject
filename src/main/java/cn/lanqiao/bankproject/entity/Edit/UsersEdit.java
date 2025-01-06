package cn.lanqiao.bankproject.entity.Edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersEdit {
    private Long id;
    private String username;
    private String bank_card;
    private String phone;
    private String address;
}
