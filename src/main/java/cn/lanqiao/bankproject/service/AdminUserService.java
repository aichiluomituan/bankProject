package cn.lanqiao.bankproject.service;/*
@author 比巴卜
@date  2025/1/7 上午11:22
@Description 
*/

import cn.lanqiao.bankproject.entity.po.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminUserService extends IService<AdminUser> {
    void selectOne(AdminUser admin);
}
