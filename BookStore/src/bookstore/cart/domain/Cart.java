package bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ��
 */
public class Cart {
	//��Ϊbid��ֵΪ���ﳵ��Ŀ,LinkedHashMap��˳�򣬶�hashmap������
	private Map<String,CartItem> map = new LinkedHashMap<String, CartItem>();

	/**
	 * �ϼ�,�����˶������������⡣
	 * @return ���ﳵ�ܼ�
	 */
	public double getTotal(){
		BigDecimal total = new BigDecimal("0");
		for (CartItem cartItem : map.values()) {
			BigDecimal subtotal = new BigDecimal(cartItem.getSubtotal()+"");
			total = total.add(subtotal);	//�ϼ�=С��֮��
		}
		return total.doubleValue();
		/*
		 * ������������ֶ�������������⣨���磺2.0-1.1=0.899999999999999999999)
		 * ������ʮ���Ƶ�10/3=3.333333333333
		 * BigInteger��BigDecimal��������ר��Ϊʮ����׼���Ĵ������ʹ󸡵�����
		 * //��1000�Ľ׳�
		 * BigInteger sum = BigInteger.valueOf(1);
		 * for(int i = 0 ; 1<1000 ; i++){
		 * 	BigInteger bi = BigInteger.valueOf(i);
		 * 	sum = sum.multiply(bi);
		 * }
		 * sysout(sum);
		 * 
		 * BigDecimal���Դ���������������
		 * 1.����BigDecimal����ʱ������ʹ��String��������
		 * 	BigDecimal d1 = new BigDecimal("2.0");
		 * 	BigDecimal d2 = new BigDecimal("1.1");
		 *  BidDecimal d3 = d1.subtract(d2);
		 *  sysout(d3)//2.0-1.1=0.9
		 */
	}
	
	/**
	 * �����Ŀ�����ﳵ
	 */
	public void add(CartItem cartItem){
		/*
		 * ��Ҫ�ж�������Ŀ�Ƿ���ͬ����ͬ��ϲ�
		 */
		if(map.containsKey(cartItem.getBook().getBid())){
			CartItem _cartItem = map.get(cartItem.getBook().getBid());//����ԭ��Ŀ
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());//��������Ŀ������Ϊ�Լ�������+����Ŀ������
			map.put(_cartItem.getBook().getBid(), _cartItem);//��һ������_cartItem.getBook().getBid()������Ŀ��bid����һ���ġ�
		} else{
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	/**
	 * ��չ��ﳵ
	 */
	public void clear(){
		map.clear();
	}
	
	/**
	 * ͨ��bidɾ��ָ����Ŀ��bid��map�ļ�
	 * @param bid
	 */
	public void delete(String bid){
		map.remove(bid);
	}
	
	/**
	 * �ҵĹ��ﳵ����ȡ������Ŀ
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
}
