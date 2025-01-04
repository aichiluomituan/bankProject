package cn.lanqiao.bankproject.entity.po;

import cn.lanqiao.bankproject.entity.enums.DateTimePatternEnum;
import cn.lanqiao.bankproject.utils.DateUtil;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 交易表
 */
@Data
@AllArgsConstructor
public class Trade implements Serializable {
	private Long id;
	  // 交易日期
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	private String bankCard;
	  // 交易类型(存款、取款、转账)
	private String tradeType;
	 // 金额
	private Double money;
	 // 交易状态
	private String tradeState;
	 // 备注
	private String remark;
	// 付款方
	private String payer;
	// 收款方
	private String payee;

	public Trade() {
	}

	public Trade(Long id, Date date, String tradeType, String tradeState, Double money, String remark) {
		this.id = id;
		this.date = date;
		this.tradeType = tradeType;
		this.tradeState = tradeState;
		this.money = money;
		this.remark = remark;
	}

	public Trade(String payee, String payer, String remark, Double money, Date date) {
		this.payee = payee;
		this.payer = payer;
		this.remark = remark;
		this.money = money;
		this.date = date;
	}

	public Trade(Date date, String tradeType, Double money, String remark, String payer, String payee) {
		this.date = date;
		this.tradeType = tradeType;
		this.money = money;
		this.remark = remark;
		this.payer = payer;
		this.payee = payee;
	}

	public Trade(Date date, Long id, String tradeType, Double money, String tradeState, String remark, String payer, String payee) {
		this.date = date;
		this.id = id;
		this.tradeType = tradeType;
		this.money = money;
		this.tradeState = tradeState;
		this.remark = remark;
		this.payer = payer;
		this.payee = payee;
	}

	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，交易日期:"+(date == null ? "空" : DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，交易类型:"+(tradeType == null ? "空" : tradeType)+"，金额:"+(money == null ? "空" : money)+"，交易状态:"+(tradeState == null ? "空" : tradeState)+"，备注:"+(remark == null ? "空" : remark);
	}
}
