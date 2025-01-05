package cn.lanqiao.bankproject.service;

import cn.lanqiao.bankproject.entity.po.UserManagement;
import cn.lanqiao.bankproject.entity.vo.UserCountAndMoneyCountVO;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface UserManagementService extends IService<UserManagement> {
    List<UserManagement> selectList( String name);

    UserManagement selectOne(@NotNull Integer userId);

    UserCountAndMoneyCountVO selectUserCountAndMoneyCount();

}
