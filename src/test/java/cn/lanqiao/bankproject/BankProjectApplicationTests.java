package cn.lanqiao.bankproject;

import cn.lanqiao.bankproject.mappers.UsersMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BankProjectApplicationTests {
    @Autowired
    private UsersMapper usersMapper;

    @Test
   public void contextLoads() {
        int i = usersMapper.totalNum();
        System.out.println(i);
    }

}
