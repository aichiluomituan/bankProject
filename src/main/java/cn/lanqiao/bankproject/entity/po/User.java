package cn.lanqiao.bankproject.entity.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 用户表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private String currentUsername; // 添加这个字段来保存当前用户名
	/**
	 * 
	 */
	private Long id;

	/**
	 * 姓名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 手机
	 */
	// @JsonIgnore
	private String phone;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 银行卡号
	 */
	private String bankCard;
	private Integer status; // 添加状态字段



	@Override
	public String toString (){
		return "id:"+(id == null ? "空" : id)+"，姓名:"+(username == null ? "空" : username)+"，密码:"+(password == null ? "空" : password)+"，手机:"+(phone == null ? "空" : phone)+"，电子邮箱:"+(email == null ? "空" : email)+"，地址:"+(address == null ? "空" : address)+"，银行卡号:"+(bankCard == null ? "空" : bankCard);
	}
}
