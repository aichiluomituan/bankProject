package cn.lanqiao.bankproject.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;


/**
 * 银行信息表
 */
@Data
public class BankInfo implements Serializable {


	/**
	 * 
	 */
	private Long id;

	/**
	 * 银行卡号
	 */
	private String bankCard;

	/**
	 * 银行卡余额
	 */
	private BigDecimal bankBalance;

	/**
	 * 开户行
	 */
	private String bankName;

	/**
	 * 交易密码
	 */
	private String tradePwd;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setBankCard(String bankCard){
		this.bankCard = bankCard;
	}

	public String getBankCard(){
		return this.bankCard;
	}

	public void setBankBalance(BigDecimal bankBalance){
		this.bankBalance = bankBalance;
	}

	public BigDecimal getBankBalance(){
		return this.bankBalance;
	}

	public void setBankName(String bankName){
		this.bankName = bankName;
	}

	public String getBankName(){
		return this.bankName;
	}

	public void setTradePwd(String tradePwd){
		this.tradePwd = tradePwd;
	}

	public String getTradePwd(){
		return this.tradePwd;
	}

	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，银行卡号:"+(bankCard == null ? "空" : bankCard)+"，银行卡余额:"+(bankBalance == null ? "空" : bankBalance)+"，开户行:"+(bankName == null ? "空" : bankName)+"，交易密码:"+(tradePwd == null ? "空" : tradePwd);
	}
}
