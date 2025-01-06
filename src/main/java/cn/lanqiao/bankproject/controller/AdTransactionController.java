package cn.lanqiao.bankproject.controller;

import cn.lanqiao.bankproject.entity.edit.AdTransactionEdit;
import cn.lanqiao.bankproject.entity.query.AdConditionSelectQuery;
import cn.lanqiao.bankproject.entity.query.AdFuzzyQuery;
import cn.lanqiao.bankproject.entity.query.AdTransactionQuery;
import cn.lanqiao.bankproject.entity.vo.PaginationResultVO;
import cn.lanqiao.bankproject.service.AdTransactionService;
import cn.lanqiao.bankproject.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 后台管理系统--交易监控
 */

@Slf4j
@RestController("AdTransactionController")
@RequestMapping("/AdTransaction")
@CrossOrigin(origins = "*")  // 添加跨域注解
public class AdTransactionController {
    @Autowired
    private AdTransactionService adTransactionService;

    /**
     * 窗口加载时 查询分页表数据
     * @return
     */
    @RequestMapping("/selectATS")
    public ResponseUtils selectATS() {
        try {
            PaginationResultVO paginationResultVO = adTransactionService.selectATS();
            // 检查是否拿到了数据
            // System.out.println("------------------------------------------------------------");
            // for (Object oj:paginationResultVO.getList()){
            //     System.out.println(oj);
            // }
            if (paginationResultVO!=null && paginationResultVO.getList().size()>0){
                //查询数据成功
                return new ResponseUtils<>(200,"查询成功",paginationResultVO);
            }else{
                //查询数据失败
                return new ResponseUtils<>(305,"查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500,"查询异常，请联系管理员");
        }
    }

    /**
     *  审核按钮，根据id查询trade数据
     * @return
     */
    @RequestMapping("/auditTrade")
    public ResponseUtils auditTrade(@RequestBody AdTransactionQuery adTransactionQuery) {
        // System.out.println(adTransactionQuery.getId());
        try {
            AdTransactionQuery ADTQ = adTransactionService.selectById(adTransactionQuery.getId());
            if (ADTQ!=null){
                //查询成功
                System.out.println("=======================================");
                System.out.println(ADTQ);
                return new ResponseUtils<>(200,"查询成功",ADTQ);
            }else {
                //查询失败
                return new ResponseUtils<>(305,"查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500,"查询异常，请联系管理员");
        }
    }

    /**
     *  审核按钮，根据id查询trade数据
     * @return
     */
    @RequestMapping("/aDEdit")
    public ResponseUtils aDEdit(@RequestBody AdTransactionEdit adTransactionEdit) {
        // System.out.println(adTransactionEdit.getTradeState());
        try {
            AdTransactionQuery AdTq = adTransactionService.updateById(adTransactionEdit.getId(), adTransactionEdit.getTradeState());
            if (AdTq!=null){
                System.out.println(AdTq);
                return new ResponseUtils<>(200,"修改成功",AdTq);
            }else {
                return new ResponseUtils<>(305,"修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500,"查询异常，请联系管理员");
        }
    }

    /**
     *  多条件查询--通过插入sql条件实现
     *   动态sql
     * @return
     */
    @RequestMapping("/conditionSelect")
    public ResponseUtils conditionSelect(@RequestBody AdConditionSelectQuery ACSQ) {
        try {
            PaginationResultVO paginationResultVO = adTransactionService.conditionSelect(ACSQ);
            if (paginationResultVO!=null && paginationResultVO.getList().size()>0){
                //查询数据成功
                return new ResponseUtils<>(200,"查询成功",paginationResultVO);
            }else{
                //查询数据失败
                return new ResponseUtils<>(305,"查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500,"查询异常，请联系管理员");
        }
    }

    /**
     *  模糊查询---通过付款方或收款方姓名查询
     *   动态sql
     * @return
     */
    @RequestMapping("/selectLikeData")
    public ResponseUtils selectLikeData(@RequestBody AdFuzzyQuery adFuzzyQuery) {
        // System.out.println(adFuzzyQuery);
        try {
            PaginationResultVO paginationResultVO = adTransactionService.selectLikeData(adFuzzyQuery);
            if (paginationResultVO!=null && paginationResultVO.getList().size()>0){
                //查询数据成功
                return new ResponseUtils<>(200,"查询成功",paginationResultVO);
            }else{
                //查询数据失败
                return new ResponseUtils<>(305,"查询失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500,"查询异常请联系管理员");
        }
    }


}





























