package cn.lanqiao.bankproject.entity.query;

import lombok.Data;

/**
 * Description: 交易监控---模糊查询
 *
 * @author KaMiGui
 * @date 2025/1/6 下午5:17
 */
@Data
public class AdFuzzyQuery {

    private String selectName; //模糊查询数据


    //接收页码信息
    private Integer pageNo; //当前页
    private final Integer pageSize = 4; //每页大小
    private Integer offset; //起始索引
}
