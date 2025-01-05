package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.entity.po.Trade;
import cn.lanqiao.bankproject.entity.po.UserManagement;
import cn.lanqiao.bankproject.entity.vo.UserCountAndMoneyCountVO;
import cn.lanqiao.bankproject.mappers.TradeMapper;
import cn.lanqiao.bankproject.mappers.UserManagementMapper;
import cn.lanqiao.bankproject.service.UserManagementService;
import cn.lanqiao.bankproject.utils.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service("userManagementServiceImpl")
public class UserManagementServiceImpl extends ServiceImpl<UserManagementMapper, UserManagement> implements UserManagementService {

    @Autowired
    private TradeMapper tradeMapper;
    @Override
    public List<UserManagement> selectList(String name) {
        LambdaQueryWrapper<UserManagement> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotEmpty(name),UserManagement::getName, name);
        return this.list(wrapper);
    }

    @Override
    public UserManagement selectOne(Integer userId) {
        return this.getById(userId);
    }

    @Override
    public UserCountAndMoneyCountVO selectUserCountAndMoneyCount() {
        UserCountAndMoneyCountVO userCountAndMoneyCountVO = new UserCountAndMoneyCountVO();
        userCountAndMoneyCountVO.setUserCount(this.count());  // User;
        LambdaQueryWrapper<Trade> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Trade::getMoney);
        List<Trade> trades = tradeMapper.selectList(wrapper);

        new Thread(()-> {
            BigDecimal moneyCount = new BigDecimal("0");
            for (Trade trade : trades) {
                try {
                    BigDecimal money = BigDecimal.valueOf(trade.getMoney());
                    moneyCount = moneyCount.add(money);
                } catch (Exception e) {
                    continue;
                }
            }
            userCountAndMoneyCountVO.setMoneyCount(String.valueOf(moneyCount));
        }).start();

LambdaQueryWrapper<UserManagement> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.between(UserManagement::getCreateTime,DateUtil.getDateFirst(),DateUtil.getDateLast());
        long molecular = this.count(wrapper1);
        wrapper1.between(UserManagement::getCreateTime,DateUtil.getOneMonthBefore(1),DateUtil.getOneMonthBefore(2));
        long denominator = this.count(wrapper1);
        if (denominator <= 0) {
            denominator = 1;
        }
        userCountAndMoneyCountVO.setCountPercentage(molecular/denominator);
        // TODO: 交易额的百百分率;

        // 重复代码;可提取;
        LambdaQueryWrapper<Trade> trade = new LambdaQueryWrapper<>();

        trade.between(Trade::getDate,DateUtil.formatDate(DateUtil.getTodayStart(0,0,0,0)),
                DateUtil.formatDate(DateUtil.getTodayStart(0,23,59,59)));
        trade.select(Trade::getMoney);
        List<Trade> tradesListToday = tradeMapper.selectList(trade);
        // 重复代码;
            BigDecimal moneyCount = new BigDecimal("0");
            for (Trade tradeCount : tradesListToday) {
                try {
                    BigDecimal money = BigDecimal.valueOf(tradeCount.getMoney());
                    moneyCount = moneyCount.add(money);
                } catch (Exception e) {
                    continue;
                }
            }

        if (moneyCount.equals(new BigDecimal("0"))) {
            userCountAndMoneyCountVO.setMoneyPercentage("今日无交易");
            return userCountAndMoneyCountVO;
        }
        trade.between(Trade::getDate,DateUtil.formatDate(DateUtil.getTodayStart(-1,0,0,0)),
                DateUtil.formatDate(DateUtil.getTodayStart(-1,23,59,59)));
        trade.select(Trade::getMoney);
        List<Trade> tradesList = tradeMapper.selectList(trade);
        BigDecimal bigDecimalToday_1 = new BigDecimal("0");
        for (Trade tradeCount : tradesList) {
            try {
                BigDecimal money = BigDecimal.valueOf(tradeCount.getMoney());
                bigDecimalToday_1 = bigDecimalToday_1.add(money);
            } catch (Exception e) {
                continue;
            }
        }

        if (bigDecimalToday_1.equals(new BigDecimal("0"))) {
            userCountAndMoneyCountVO.setMoneyPercentage("昨日无交易,今日为增长");
            return userCountAndMoneyCountVO;
        }



        String s = String.valueOf(moneyCount.subtract(bigDecimalToday_1)
                .divide(bigDecimalToday_1, 2, RoundingMode.HALF_UP));
        if (moneyCount.subtract(bigDecimalToday_1).doubleValue() < 0) {
            s = "-"+s+"%";
        }
        userCountAndMoneyCountVO
                .setMoneyPercentage(s);
        return userCountAndMoneyCountVO;
     }

}
