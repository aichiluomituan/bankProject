package cn.lanqiao.bankproject.utils;

import cn.lanqiao.bankproject.constans.ConStantsFinal;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("redisComponent")
public class RedisComponent {

    @Autowired
    private RedisUtils redisUtils;

    public String getCodeKey (String checkCodeKey) {
        if (StringUtils.isEmpty(checkCodeKey)) {
            return null;
        }
        return (String)redisUtils.get(ConStantsFinal.CODE_CACHE_KEY+checkCodeKey);
    }
}
