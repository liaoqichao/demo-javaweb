package bookstore.order.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.cart.domain.Cart;
import bookstore.cart.domain.CartItem;
import bookstore.order.domain.Order;
import bookstore.order.domain.OrderItem;
import bookstore.order.service.OrderException;
import bookstore.order.service.OrderService;
import bookstore.user.domain.User;
import lqcUtils.CommonUtils;
import lqcUtils.PaymentUtil;
import lqcUtils.servlet.BaseServlet;

/**
 * 订单的Servlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	
	/**
	 * 添加订单
	 * 把session_cart拿过来用来生成Order对象
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.从session中得到session_cart
		 * 2.使用cart生成order对象
		 * 3.调用service完成添加订单
		 * 4.保存order到request，并转发到/jsps/order/desc.jsp
		 */
		//1.如果没登录用户就拿不到，最后会用过滤器过滤掉没有登录的用户。
		Cart cart = (Cart) request.getSession().getAttribute("session_cart");
		//2.购物车和订单对应
		Order order = new Order();
		order.setOid(CommonUtils.uuid());//设置主键
		order.setOrdertime(new Date());//设置下单时间
		order.setState(1);//设置订单状态，1表示未付款。
		order.setOwner((User)request.getSession().getAttribute("session_user"));
		order.setTotal(cart.getTotal());//设置订单的合计，从购物车中获取。
		//地址时订单生成后，付款前设置的，所以这里不设置。
		
		/*
		 * 创建订单条目的集合。购物车条目和订单条目对应
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		//循环遍历所有cartItem，使每个CartItem对象创建OrderItem对象，并添加到集合中
		for(CartItem cartItem : cart.getCartItems()){
			OrderItem oi = new OrderItem();//创建订单条目
			oi.setIid(CommonUtils.uuid());//设置主键
			oi.setCount(cartItem.getCount());//设置条目的数量
			oi.setBook(cartItem.getBook());//设置条目的图书
			oi.setSubtotal(cartItem.getSubtotal());//设置条目的小计
			oi.setOrder(order);//添加关联关系，这个order就是这个条目要被放到的order。
			orderItemList.add(oi);//把订单条目添加到集合中
		}
		order.setOrderItemList(orderItemList);//把订单条目集合添加到订单中
		
		//3.清空购物车，调用service方法
		cart.clear();//为什么不放在service？都可以，因为这个是在session中获取的，如果在service中调用需要传递cart
					 //如果购物车存在数据库中就在业务层完成。
		orderService.add(order);
		
		//4.
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
	}

	/**
	 * 我的订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.从session中获得当前用户，在获取其UUID
		 * 2.使用uuid调用orderservice#myorders得到该用户的所有订单List<Order>
		 * 3.把订单列表保存到request域中，并转发到/jsps/order/list.jsp
		 */
		//1.
		User user = (User) request.getSession().getAttribute("session_user");
		//2.
		List<Order> orderList = orderService.myOrders(user.getUid());
		//3.
		request.setAttribute("orderList", orderList);
		return "f:/jsps/order/list.jsp";
	}
	
	/**
	 * 加载订单：在订单列表页面点击付款，调用load方法，传递oid参数，查询得到order，保存request域，转发到/jsps/order/desc.jsp
	 * 加载XX就是通过主键查询数据库获取对象！！
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.得到oid参数
		 * 2.使用oid调用service方法得到order
		 * 3.保存到request域中，转发到/jsps/order/desc.jsp
		 */
		//1.
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "f:/jsps/order/desc.jsp";
	}

	/**
	 * 确认收货
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取oid参数
		 * 2.调用service方法
		 * 	> 如果有异常保存异常信息，转发到msg.jsp
		 *  > 如果没有异常保存异常信息，转发到msg.jsp
		 */
		//1.
		String oid = request.getParameter("oid");
		//2.
		try {
			orderService.confirm(oid);
			request.setAttribute("msg", "完成确认收货，交易成功！");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * 支付之去银行
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * https://www.yeepay.com/app-merchant-proxy/node
		 * 1.准备易宝所需要的13+1个参数
		 * 2.连接易宝的网址和13+1个参数
		 * 3.重定向到易宝
		 */
		//1.
		//得到配置文件，里面有p1_MerId参数和秘钥keyValue的值
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(in);
		
		String p0_Cmd = "Buy";//业务类型,固定值“Buy”.
		String p1_MerId = props.getProperty("p1_MerId");//这个值来自于配置文件。商户编号.商户在易宝支付系统的唯一身份标识.获取方式见“如何获得商户编号”
		String p2_Order = request.getParameter("oid");//商户订单号
		String p3_Amt = "0.01";//支付金额.实际上是通过oid查询order，得到order.count
		String p4_Cur = "CNY";//交易币种，固定为“CNY”
		String p5_Pid = "";//商品名称,这里不给
		String p6_Pcat = "";//商品种类,这里不给
		String p7_Pdesc = "";//商品描述,这里不给
		String p8_Url = props.getProperty("p8_Url");//商户接收支付成功数据的地址。注意：如不填p8_Url的参数值支付成功后您将得不到支付成功的通知。
		String p9_SAF = "";//送货地址。这里不给值。为“1”: 需要用户将送货地址留在易宝支付系统;为“0”: 不需要，默认为 ”0”.
		String pa_MP = "";//商户扩展信息。不给值
		String pd_FrpId = request.getParameter("pd_FrpId");//支付通道编码，从表单来
		String pr_NeedResponse = "1";//应答机制，固定值为1
		/*
		 * 计算hmac，13个参数+keyValue。通过PayUtils
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur,
				p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId,
				pr_NeedResponse, keyValue);//签名数据
		
		//2.
		StringBuilder url = new StringBuilder(props.getProperty("url"));
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);
		
//		System.out.println(url);
		//3.重定向到易宝
		response.sendRedirect(url.toString());

		return null;
	}
	
	/**
	 * 这个方法是易宝回调方法，必须判断调用本方法的是不是易宝
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String back(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.获取11+1参数
		 * 2.校验访问者的身份是否为易宝(hmac.易宝发回的hmac是11个参数+自己密钥生成的。我们通过获得11个参数，通过自己的密钥生成hmac
		 * 	如果相同，则验证为访问者是易宝)
		 * 3.查看订单状态是否为1，如果为1，修改为2，（加积分，可能有这个业务）。如果不是1，数据库什么都不修改。
		 * 4.判断回调方式（1：重定向,2：点对点）
		 * 	> 如果是点对点，回馈SUCCESS字符串,否则易宝会一直重发交易成功信息。
		 * 5.保存成功信息转发到msg.jsp
		 */
		
		/*
		 * 1.获取11+1个参数
		 */
		String p1_MerId = request.getParameter("p1_MerId");//商户编号
		String r0_Cmd = request.getParameter("r0_Cmd");//业务类型,固定为BUY
		String r1_Code = request.getParameter("r1_Code");//支付结果，固定为1表示交易成功
		String r2_TrxId = request.getParameter("r2_TrxId");//易宝支付交易流水号
		String r3_Amt = request.getParameter("r3_Amt");//支付金额
		String r4_Cur = request.getParameter("r4_Cur");//交易币种
		String r5_Pid = request.getParameter("r5_Pid");//商品名称
		String r6_Order = request.getParameter("r6_Order");//商户订单号,就是oid，后面有用
		String r7_Uid = request.getParameter("r7_Uid");//易宝支付会员ID
		String r8_MP = request.getParameter("r8_MP");//商户扩展信息
		String r9_BType = request.getParameter("r9_BType");//交易结果返回类型为“1”: 浏览器重定向;为“2”: 服务器点对点通讯
		String hmac = request.getParameter("hmac");
	
		/*
		 * 2.校验访问者是否为易宝
		 */
		//得到配置文件，里面有p1_MerId参数和秘钥keyValue的值
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(in);
		String keyValue = props.getProperty("keyValue");
		//有专门的方法校验回调
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId,
				r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		if(!bool){//校验失败
			request.setAttribute("msg", "你不是什么好东西" );
			return "f:/jsps/msg.jsp";
		}
		//校验成功
		/*
		 * 3.获取订单状态，确定是否要修改订单状态以及添加积分等业务操作。
		 */
		orderService.pay(r6_Order);//有可能对数据库进行操作，也可能不操作
		/*
		 * 4.判断当前回调方式，如果为点对点需要以success开头的字符串
		 */
		if(r9_BType.equals("1")){//浏览器重定向
			
		} else if(r9_BType.equals("2")){//点对点，这里需要回馈
			response.getWriter().print("success");
		}
		/*
		 * 5.保存成功信息，转发到msg.jsp
		 */
		request.setAttribute("msg", "支付成功！等待卖家发货！");
		return "f:/jsps/msg.jsp";
	}
	
}
