package springmybatis.po;

import java.util.ArrayList;
import java.util.List;

/**
 * demo5 = 输入参数为包装类型的pojo
 * demo9 = 添加idList。输入多个id，使用foreach标签解析
 * 优点：可以从视图层传到dao层都只用这个对象
 * @author Liaoqichao
 * @date 2016年3月13日
 */
public class UserQueryVo {

	// 包装所需要的查询条件
	private UserCustom userCustom;
	// 包装订单、商品等等...

	// 传入多个id
	private List<Integer> idList = new ArrayList<Integer>();
	
	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	
	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}
}
