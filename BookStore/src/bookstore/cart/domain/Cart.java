package bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车类
 */
public class Cart {
	//键为bid，值为购物车条目,LinkedHashMap有顺序，而hashmap是乱序。
	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();

	/**
	 * 合计,处理了二进制运算问题。
	 * @return 购物车总价
	 */
	public double getTotal(){
		BigDecimal total = new BigDecimal("0");
		for (CartItem cartItem : map.values()) {
			BigDecimal subtotal = new BigDecimal(cartItem.getSubtotal()+"");
			total = total.add(subtotal);	//合计=小计之和
		}
		return total.doubleValue();
		/*
		 * 但是这样会出现二进制运算的问题（例如：2.0-1.1=0.899999999999999999999)
		 * 类似于十进制的10/3=3.333333333333
		 * BigInteger和BigDecimal这两个类专门为十进制准备的大整数和大浮点数类
		 * //求1000的阶乘
		 * BigInteger sum = BigInteger.valueOf(1);
		 * for(int i = 0 ; 1<1000 ; i++){
		 * 	BigInteger bi = BigInteger.valueOf(i);
		 * 	sum = sum.multiply(bi);
		 * }
		 * sysout(sum);
		 * 
		 * BigDecimal可以处理二进制运算的误差。
		 * 1.创建BigDecimal对象时，必须使用String构造器。
		 * 	BigDecimal d1 = new BigDecimal("2.0");
		 * 	BigDecimal d2 = new BigDecimal("1.1");
		 *  BidDecimal d3 = d1.subtract(d2);
		 *  sysout(d3)//2.0-1.1=0.9
		 */
	}
	
	/**
	 * 添加条目到购物车
	 */
	public void add(CartItem cartItem){
		/*
		 * 需要判断两个条目是否相同，相同则合并
		 */
		if(map.containsKey(cartItem.getBook().getBid())){
			CartItem _cartItem = map.get(cartItem.getBook().getBid());//返回原条目
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());//设置老条目的数量为自己的数量+新条目的数量
			map.put(_cartItem.getBook().getBid(), _cartItem);//第一个参数_cartItem.getBook().getBid()和新条目的bid都是一样的。
		} else{
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		map.clear();
	}
	
	/**
	 * 通过bid删除指定条目，bid是map的键
	 * @param bid
	 */
	public void delete(String bid){
		map.remove(bid);
	}
	
	/**
	 * 我的购物车，获取所有条目
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
