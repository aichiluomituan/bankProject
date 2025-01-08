package cn.lanqiao.bankproject.service.impl;/*
@author 比巴卜
@date  2025/1/7 上午11:22
@Description 
*/

import cn.lanqiao.bankproject.common.ResultCodeEnum;
import cn.lanqiao.bankproject.common.managementException;
import cn.lanqiao.bankproject.entity.po.AdminUser;
import cn.lanqiao.bankproject.mappers.AdminUserMapper;
import cn.lanqiao.bankproject.service.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {
    @Override
    public void selectOne(AdminUser admin) {
        if (admin == null) {
            throw new managementException(ResultCodeEnum.INCORRECT_ID_CODE);
        }
        LambdaQueryWrapper<AdminUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(admin.getUsername() != null,AdminUser::getUsername,admin.getUsername());
        AdminUser one = this.getOne(lambdaQueryWrapper);
        if (one == null) {
            throw new managementException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        if (!one.getPassword().equals(admin.getPassword())) {
            throw new managementException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR_PASSWORD);
        }
    }
}
