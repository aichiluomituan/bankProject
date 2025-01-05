package cn.lanqiao.bankproject.mappers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCountAndMoneyCountVO {
    private Long userCount;
    private String moneyCount;
    private Long countPercentage;
    private String moneyPercentage;
}
