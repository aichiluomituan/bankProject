package cn.lanqiao.bankproject.service;

import cn.lanqiao.bankproject.entity.query.AdConditionSelectQuery;
import cn.lanqiao.bankproject.entity.query.AdFuzzyQuery;
import cn.lanqiao.bankproject.entity.query.AdTransactionQuery;
import cn.lanqiao.bankproject.entity.vo.PaginationResultVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description:
 * @author KaMiGui
 * @date 2025/1/3 下午6:09
 */
public interface AdTransactionService {
    /**
     *  加载窗口时 查询分页数据
     */
    PaginationResultVO selectATS();

    /**
     * 根据id查询trade数据
     */
    AdTransactionQuery selectById(@Param("id") int id);

    /**
     *  根据id修改交易状态
     */
    AdTransactionQuery updateById(@Param("id") int id,@Param("tradeState") String tradeState);

    /**
     *   多条件查询
     */
    PaginationResultVO conditionSelect(AdConditionSelectQuery adConditionSelectQuery);


    /**
     *  模糊查询--通过付款方或收款方姓名查询数据
     */
    PaginationResultVO selectLikeData(AdFuzzyQuery adFuzzyQuery);


}
