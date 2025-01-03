package cn.lanqiao.bankproject.entity.po;

import cn.lanqiao.bankproject.entity.enums.DateTimePatternEnum;
import cn.lanqiao.bankproject.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;


/**
 * 交易表
 */
public class Trade implements Serializable {


	/**
	 * 
	 */
	private Long id;

	/**
	 * 交易日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;

	/**
	 * 交易类型
	 */
	private String tradeType;

	/**
	 * 金额
	 */
	private BigDecimal money;

	/**
	 * 交易状态
	 */
	private String tradeState;

	/**
	 * 备注
	 */
	private String remark;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return this.date;
	}

	public void setTradeType(String tradeType){
		this.tradeType = tradeType;
	}

	public String getTradeType(){
		return this.tradeType;
	}

	public void setMoney(BigDecimal money){
		this.money = money;
	}

	public BigDecimal getMoney(){
		return this.money;
	}

	public void setTradeState(String tradeState){
		this.tradeState = tradeState;
	}

	public String getTradeState(){
		return this.tradeState;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，交易日期:"+(date == null ? "空" : DateUtil.format(date, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern()))+"，交易类型:"+(tradeType == null ? "空" : tradeType)+"，金额:"+(money == null ? "空" : money)+"，交易状态:"+(tradeState == null ? "空" : tradeState)+"，备注:"+(remark == null ? "空" : remark);
	}
}
