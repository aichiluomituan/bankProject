package cn.lanqiao.bankproject.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageHelper<T> {
    private int pageSize;//一页显示多少条
    private int Pages;//一共有多少页
    private int pageNum;//当前页
    private List<T> list;//数据
}
