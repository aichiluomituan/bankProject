package cn.lanqiao.bankproject.entity.query;



/**
 * 用户表参数
 */
public class UserQuery extends BaseParam {


	/**
	 * 
	 */
	private Long id;

	/**
	 * 姓名
	 */
	private String username;

	private String usernameFuzzy;

	/**
	 * 密码
	 */
	private String password;

	private String passwordFuzzy;

	/**
	 * 手机
	 */
	private String phone;

	private String phoneFuzzy;

	/**
	 * 电子邮箱
	 */
	private String email;

	private String emailFuzzy;

	/**
	 * 地址
	 */
	private String address;

	private String addressFuzzy;

	/**
	 * 银行卡号
	 */
	private String bankCard;

	private String bankCardFuzzy;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setUsernameFuzzy(String usernameFuzzy){
		this.usernameFuzzy = usernameFuzzy;
	}

	public String getUsernameFuzzy(){
		return this.usernameFuzzy;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setPasswordFuzzy(String passwordFuzzy){
		this.passwordFuzzy = passwordFuzzy;
	}

	public String getPasswordFuzzy(){
		return this.passwordFuzzy;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setPhoneFuzzy(String phoneFuzzy){
		this.phoneFuzzy = phoneFuzzy;
	}

	public String getPhoneFuzzy(){
		return this.phoneFuzzy;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setEmailFuzzy(String emailFuzzy){
		this.emailFuzzy = emailFuzzy;
	}

	public String getEmailFuzzy(){
		return this.emailFuzzy;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return this.address;
	}

	public void setAddressFuzzy(String addressFuzzy){
		this.addressFuzzy = addressFuzzy;
	}

	public String getAddressFuzzy(){
		return this.addressFuzzy;
	}

	public void setBankCard(String bankCard){
		this.bankCard = bankCard;
	}

	public String getBankCard(){
		return this.bankCard;
	}

	public void setBankCardFuzzy(String bankCardFuzzy){
		this.bankCardFuzzy = bankCardFuzzy;
	}

	public String getBankCardFuzzy(){
		return this.bankCardFuzzy;
	}

}
