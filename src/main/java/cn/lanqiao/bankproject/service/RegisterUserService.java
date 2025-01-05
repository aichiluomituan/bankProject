package cn.lanqiao.bankproject.service;

import cn.lanqiao.bankproject.entity.dto.RegisterDTO;
import cn.lanqiao.bankproject.entity.po.RegisterUser;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.constraints.NotNull;

public interface RegisterUserService extends IService<RegisterUser> {
    void Register(@NotNull RegisterDTO registerDTO);
}
