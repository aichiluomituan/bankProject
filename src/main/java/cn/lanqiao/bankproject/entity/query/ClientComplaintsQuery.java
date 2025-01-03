package cn.lanqiao.bankproject.entity.query;



/**
 * 客诉表参数
 */
public class ClientComplaintsQuery extends BaseParam {


	/**
	 * 
	 */
	private Long id;

	/**
	 * 类型
	 */
	private String type;

	private String typeFuzzy;

	/**
	 * 标题
	 */
	private String title;

	private String titleFuzzy;

	/**
	 * 内容
	 */
	private String context;

	private String contextFuzzy;

	/**
	 * 联系方式
	 */
	private String number;

	private String numberFuzzy;


	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return this.id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setTypeFuzzy(String typeFuzzy){
		this.typeFuzzy = typeFuzzy;
	}

	public String getTypeFuzzy(){
		return this.typeFuzzy;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setTitleFuzzy(String titleFuzzy){
		this.titleFuzzy = titleFuzzy;
	}

	public String getTitleFuzzy(){
		return this.titleFuzzy;
	}

	public void setContext(String context){
		this.context = context;
	}

	public String getContext(){
		return this.context;
	}

	public void setContextFuzzy(String contextFuzzy){
		this.contextFuzzy = contextFuzzy;
	}

	public String getContextFuzzy(){
		return this.contextFuzzy;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return this.number;
	}

	public void setNumberFuzzy(String numberFuzzy){
		this.numberFuzzy = numberFuzzy;
	}

	public String getNumberFuzzy(){
		return this.numberFuzzy;
	}

}
