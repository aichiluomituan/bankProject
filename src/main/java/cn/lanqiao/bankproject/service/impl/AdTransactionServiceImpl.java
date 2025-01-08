package cn.lanqiao.bankproject.service.impl;

import cn.lanqiao.bankproject.entity.query.AdConditionSelectQuery;
import cn.lanqiao.bankproject.entity.query.AdFuzzyQuery;
import cn.lanqiao.bankproject.entity.query.AdTransactionQuery;
import cn.lanqiao.bankproject.entity.vo.PaginationResultVO;
import cn.lanqiao.bankproject.mappers.AdTransactionMapper;
import cn.lanqiao.bankproject.mappers.UserMapper;
import cn.lanqiao.bankproject.service.AdTransactionService;
import cn.lanqiao.bankproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * @author KaMiGui
 * @date 2025/1/3 下午6:08
 */
@Service
public class AdTransactionServiceImpl implements AdTransactionService {
    @Autowired
    private AdTransactionMapper adTransactionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public PaginationResultVO selectATS() {
        //规定每页的显示的总个数为4
        int pageSize = 4;
        //查询数据的总数
        int totalCount = adTransactionMapper.countATS();
        //通过数据总数和每页个数去得到页码等信息
        int pageTotal = (int) Math.ceil((double) totalCount / pageSize);
        //当前页为1
        int pageNo = 1;
        //获取到查询的起始索引
        int offset = (pageNo-1) * pageSize;
        //从trade表内查询到数据,将offset和pageSize传入到sql中
        List<AdTransactionQuery> adTransactionQueries = adTransactionMapper.selectATS(offset, pageSize);
        if (!adTransactionQueries.isEmpty()){
            //查询到数据
            //此时还需要付款方和收款方的卡号
            for (AdTransactionQuery adtq : adTransactionQueries) {
                //付款方卡号
                String payerBankCard = userService.privacyTreatment(userMapper.selectBankCardAd(adtq.getPayer()));
                //收款方卡号
                String payeeBankCard = userService.privacyTreatment(userMapper.selectBankCardAd(adtq.getPayee()));
                //将卡号添加到adTransactionQueries集合内
                adtq.setPayerBankCard(payerBankCard);
                adtq.setPayeeBankCard(payeeBankCard);
                // System.out.println(adtq);
            }
            //将分页数据和页码数据赋值给PaginationResultVO然后返回给前端
            PaginationResultVO paginationResultVO = new PaginationResultVO();
            paginationResultVO.setPageSize(pageSize);
            paginationResultVO.setTotalCount(totalCount);
            paginationResultVO.setPageNo(pageNo);
            paginationResultVO.setPageTotal(pageTotal);
            paginationResultVO.setList(adTransactionQueries);
            return paginationResultVO;
        }else {
            //没有查询到数据
            return null;
        }
    }

    @Override
    public AdTransactionQuery selectById(int id) {
        AdTransactionQuery adTransactionQuery = adTransactionMapper.selectById(id);
        if (adTransactionQuery!=null){
            //查询成功,还需要查询收款人和付款人的卡号
            String payeeBankCard = userMapper.selectBankCardAd(adTransactionQuery.getPayee());
            String payerBankCard = userMapper.selectBankCardAd(adTransactionQuery.getPayer());
            //将卡号添加在adTransactionQuery
            adTransactionQuery.setPayeeBankCard(payeeBankCard);
            adTransactionQuery.setPayerBankCard(payerBankCard);
            return adTransactionQuery;
        }else {
            //查询失败
            return null;
        }
    }

    @Override
    public AdTransactionQuery updateById(int id, String tradeState) {
        int result = adTransactionMapper.updateById(id, tradeState);
        AdTransactionQuery adTransactionQuery = adTransactionMapper.selectById(id);
        if (result>0){
            return adTransactionQuery;
        }else {
            return adTransactionQuery;
        }
    }

    @Override
    public PaginationResultVO conditionSelect(AdConditionSelectQuery adConditionSelectQuery) {
        //通过当前页和每页大小获取起始索引,先默认当前页为1
        // adConditionSelectQuery.setPageNo(1);
        //查询数据的总数,这里不能调用countATS。需要查询特定条件查询到的数据条数
        int totalCount = adTransactionMapper.countCondition(adConditionSelectQuery);
        //通过数据总数和每页个数去得到页码等信息,总页数
        int pageTotal = (int) Math.ceil((double) totalCount / adConditionSelectQuery.getPageSize());
        //做一个判断：当总页数小于当前页的时候。设定当前页为1 避免起始索引超出数据实际长度
        if (pageTotal<adConditionSelectQuery.getPageNo()){
            //设定当前页为1
            adConditionSelectQuery.setPageNo(1);
        }
        int offset = (adConditionSelectQuery.getPageNo() - 1) * adConditionSelectQuery.getPageSize();
        adConditionSelectQuery.setOffset(offset);
        System.out.println(adConditionSelectQuery);
        System.out.println("conditionSelect查询前面"+adConditionSelectQuery);
        List<AdTransactionQuery> ACSQ = adTransactionMapper.conditionSelect(adConditionSelectQuery);
        // System.out.println(ACSQ);
        if (!ACSQ.isEmpty()){
            //通过查询到的payer和payee去user表查对应的卡号
            for (AdTransactionQuery adtq : ACSQ) {
                adtq.setPayeeBankCard(userService.privacyTreatment(userMapper.selectBankCardAd(adtq.getPayee())));
                adtq.setPayerBankCard(userService.privacyTreatment(userMapper.selectBankCardAd(adtq.getPayer())));
                System.out.println("=====================================================");
                System.out.println(adtq);
            }
            //将分页数据和页码数据赋值给PaginationResultVO然后返回给前端
            PaginationResultVO paginationResultVO = new PaginationResultVO();
            paginationResultVO.setPageSize(adConditionSelectQuery.getPageSize());
            paginationResultVO.setTotalCount(totalCount);
            paginationResultVO.setPageNo(adConditionSelectQuery.getPageNo());
            paginationResultVO.setPageTotal(pageTotal);
            paginationResultVO.setList(ACSQ);
            return paginationResultVO;
        }else {
            //这里分两种情况，一是数据查询错误。二是查询出的数据确实为null
            return null;
        }
    }

    @Override
    public PaginationResultVO selectLikeData(AdFuzzyQuery adFuzzyQuery) {
        //获取到符合条件的数据总数
        int totalCount = adTransactionMapper.selectLikeDataCount(adFuzzyQuery);
        //获取到总页码数
        int pageTotal = (int) Math.ceil((double) totalCount / adFuzzyQuery.getPageSize());
        //做一个判断：当总页数小于当前页的时候。设定当前页为1 避免起始索引超出数据实际长度
        if (pageTotal<adFuzzyQuery.getPageNo()){
            //设定当前页为1
            adFuzzyQuery.setPageNo(1);
        }
        //通过当前页码获取到起始索引
        int offset = (adFuzzyQuery.getPageNo() - 1) * adFuzzyQuery.getPageSize();
        adFuzzyQuery.setOffset(offset);
        System.out.println(adFuzzyQuery);
        //通过查询到的payee查询卡号
        List<AdTransactionQuery> ADTQ = adTransactionMapper.selectLikeData(adFuzzyQuery);
        if (!ADTQ.isEmpty()){
            for (AdTransactionQuery adtq : ADTQ) {
                adtq.setPayeeBankCard(userService.privacyTreatment(userMapper.selectBankCardAd(adtq.getPayee())));
                adtq.setPayerBankCard(userService.privacyTreatment(userMapper.selectBankCardAd(adtq.getPayer())));
                System.out.println("=======================================================");
                System.out.println(adtq);
            }
            //将分页数据和页码数据赋值给PaginationResultVO然后返回给前端
            PaginationResultVO paginationResultVO = new PaginationResultVO();
            paginationResultVO.setPageSize(adFuzzyQuery.getPageSize());
            paginationResultVO.setTotalCount(totalCount);
            paginationResultVO.setPageNo(adFuzzyQuery.getPageNo());
            paginationResultVO.setPageTotal(pageTotal);
            paginationResultVO.setList(ADTQ);
            return paginationResultVO;
        }else {
            //查询失败或者暂无数据
            return null;
        }

    }
}





















