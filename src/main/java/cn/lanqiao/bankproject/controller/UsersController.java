package cn.lanqiao.bankproject.controller;

import cn.lanqiao.bankproject.entity.Edit.UsersEdit;
import cn.lanqiao.bankproject.entity.query.PageHelperQuery;
import cn.lanqiao.bankproject.service.UsersService;
import cn.lanqiao.bankproject.utils.ResponseUtils;
import cn.lanqiao.bankproject.mappers.UsersMapper;
import cn.lanqiao.bankproject.service.UsersService;
import cn.lanqiao.bankproject.entity.query.UsersQuery;
import cn.lanqiao.bankproject.entity.po.Users;
import cn.lanqiao.bankproject.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/list")
    public ResponseUtils list(@RequestBody PageHelperQuery pageHelperQuery) {
        // PageHelper<Users> pageHelper = usersService.selectUsersList(pageHelperQuery.getStatus(), pageHelperQuery.getUsername(), pageHelperQuery.getPageNum(), pageHelperQuery.getPageSize());
        // return new ResponseUtils<>(200, "分页查询成功成功", pageHelper);
        // 打印接收到的参数
        System.out.println("接收到的查询参数: " + pageHelperQuery);

        try {
            PageHelper<Users> result = usersService.selectUsersList(
                    pageHelperQuery.getStatus(),
                    pageHelperQuery.getUsername(),
                    pageHelperQuery.getPageNum(),
                    pageHelperQuery.getPageSize()
            );
            // 打印查询结果
            System.out.println("查询结果: " + result);
            return new ResponseUtils<>(200, "查询成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtils<>(500, "查询失败: " + e.getMessage(), null);
        }
    }
    @RequestMapping("/update")
    public ResponseUtils update(@RequestBody UsersEdit usersEdit) {
        try {
            int update = usersService.update(usersEdit);
            if(update==1){
                return  new ResponseUtils(200,"修改成功");
            }else{
                return  new ResponseUtils(300,"修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
