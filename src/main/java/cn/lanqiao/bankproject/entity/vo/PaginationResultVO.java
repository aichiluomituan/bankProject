package cn.lanqiao.bankproject.entity.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResultVO<T> {

	private Integer totalCount; //数据总数
	private Integer pageSize; //每页大小
	private Integer pageNo; //当前页
	private Integer pageTotal; //总页数
	private List<T> list = new ArrayList<T>(); //数据

}
