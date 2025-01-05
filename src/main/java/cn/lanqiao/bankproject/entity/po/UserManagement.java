package cn.lanqiao.bankproject.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("User_Management")
public class UserManagement {
    @TableId(type= IdType.AUTO)
    private Integer UserId;
    @TableField("name")
    private String name;
    @TableField("account_no")
    private String accountNo;
    @TableField("phone")
    private String phone;
    @TableField("register_time")
    private LocalDateTime registerTime;
    @TableField("balance")
    private Double balance;
    @TableField("status")
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    @TableField("create_time")
    private LocalDateTime createTime;
    @TableField("update_time")
    private LocalDateTime updateTime;

}
