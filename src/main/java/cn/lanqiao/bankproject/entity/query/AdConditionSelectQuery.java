package cn.lanqiao.bankproject.entity.query;

import lombok.Data;

/**
 * Description: 交易监控 多条件查询
 *
 * @author KaMiGui
 * @date 2025/1/5 下午3:26
 */
@Data
public class AdConditionSelectQuery {

    //接收条件
    private String tradeTypeAd; //交易类型
    private String tradeStateAd; //交易状态
    private String preTime; //交易最早时间
    private String endTime; //交易最晚时间
    private Integer minMoney; //交易最小金额
    private Integer maxMoney; //交易最大金额
    //接收页码信息
    private Integer pageNo; //当前页
    private final Integer pageSize = 4; //每页大小
    private Integer offset; //起始索引

    private String selectName; //模糊查询数据
}
