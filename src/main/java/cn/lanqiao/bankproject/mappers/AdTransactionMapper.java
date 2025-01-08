package cn.lanqiao.bankproject.mappers;

import cn.lanqiao.bankproject.entity.query.AdConditionSelectQuery;
import cn.lanqiao.bankproject.entity.query.AdFuzzyQuery;
import cn.lanqiao.bankproject.entity.query.AdTransactionQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description:AdTransactionController对应的mapper
 *
 * @author KaMiGui
 * @date 2025/1/3 下午12:19
 */
public interface AdTransactionMapper {
    /**
     *  加载窗口时 查询分页数据
     */
    List<AdTransactionQuery> selectATS(@Param("offset") int offset, @Param("pageSize") int pageSize);

    /**
     *  查询数据的总条数
     */
    @Select("select count(*) from trade")
    int countATS();

    /**
     * 根据id查询trade数据
     */

    AdTransactionQuery selectById(@Param("id") int id);

    /**
     *  根据id修改交易状态
     */
    int updateById(@Param("id") int id,@Param("tradeState") String tradeState);


    /**
     *   多条件查询
     */
    List<AdTransactionQuery> conditionSelect(AdConditionSelectQuery adConditionSelectQuery);

    /**
     *  多条件查询--查询数据总数
     */
    int countCondition(AdConditionSelectQuery adConditionSelectQuery);

    /**
     *  模糊查询--通过付款方或收款方姓名查询数据
     */
    List<AdTransactionQuery> selectLikeData(AdFuzzyQuery adFuzzyQuery);

    /**
     *  模糊查询--通过用户名查询到数据总数
     */
    int selectLikeDataCount(AdFuzzyQuery adFuzzyQuery);
}
