package cn.lanqiao.bankproject.entity.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageHelperQuery {
    private String status;
    private  String username;
    private Integer pageNum;
    private Integer pageSize;
}
