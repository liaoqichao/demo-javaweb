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
 * ������Servlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	
	/**
	 * ��Ӷ���
	 * ��session_cart�ù�����������Order����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��session�еõ�session_cart
		 * 2.ʹ��cart����order����
		 * 3.����service�����Ӷ���
		 * 4.����order��request����ת����/jsps/order/desc.jsp
		 */
		//1.���û��¼�û����ò����������ù��������˵�û�е�¼���û���
		Cart cart = (Cart) request.getSession().getAttribute("session_cart");
		//2.���ﳵ�Ͷ�����Ӧ
		Order order = new Order();
		order.setOid(CommonUtils.uuid());//��������
		order.setOrdertime(new Date());//�����µ�ʱ��
		order.setState(1);//���ö���״̬��1��ʾδ���
		order.setOwner((User)request.getSession().getAttribute("session_user"));
		order.setTotal(cart.getTotal());//���ö����ĺϼƣ��ӹ��ﳵ�л�ȡ��
		//��ַʱ�������ɺ󣬸���ǰ���õģ��������ﲻ���á�
		
		/*
		 * ����������Ŀ�ļ��ϡ����ﳵ��Ŀ�Ͷ�����Ŀ��Ӧ
		 */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		//ѭ����������cartItem��ʹÿ��CartItem���󴴽�OrderItem���󣬲���ӵ�������
		for(CartItem cartItem : cart.getCartItems()){
			OrderItem oi = new OrderItem();//����������Ŀ
			oi.setIid(CommonUtils.uuid());//��������
			oi.setCount(cartItem.getCount());//������Ŀ������
			oi.setBook(cartItem.getBook());//������Ŀ��ͼ��
			oi.setSubtotal(cartItem.getSubtotal());//������Ŀ��С��
			oi.setOrder(order);//��ӹ�����ϵ�����order���������ĿҪ���ŵ���order��
			orderItemList.add(oi);//�Ѷ�����Ŀ��ӵ�������
		}
		order.setOrderItemList(orderItemList);//�Ѷ�����Ŀ������ӵ�������
		
		//3.��չ��ﳵ������service����
		cart.clear();//Ϊʲô������service�������ԣ���Ϊ�������session�л�ȡ�ģ������service�е�����Ҫ����cart
					 //������ﳵ�������ݿ��о���ҵ�����ɡ�
		orderService.add(order);
		
		//4.
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
	}

	/**
	 * �ҵĶ���
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��session�л�õ�ǰ�û����ڻ�ȡ��UUID
		 * 2.ʹ��uuid����orderservice#myorders�õ����û������ж���List<Order>
		 * 3.�Ѷ����б��浽request���У���ת����/jsps/order/list.jsp
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
	 * ���ض������ڶ����б�ҳ�����������load����������oid��������ѯ�õ�order������request��ת����/jsps/order/desc.jsp
	 * ����XX����ͨ��������ѯ���ݿ��ȡ���󣡣�
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.�õ�oid����
		 * 2.ʹ��oid����service�����õ�order
		 * 3.���浽request���У�ת����/jsps/order/desc.jsp
		 */
		//1.
		request.setAttribute("order", orderService.load(request.getParameter("oid")));
		return "f:/jsps/order/desc.jsp";
	}

	/**
	 * ȷ���ջ�
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡoid����
		 * 2.����service����
		 * 	> ������쳣�����쳣��Ϣ��ת����msg.jsp
		 *  > ���û���쳣�����쳣��Ϣ��ת����msg.jsp
		 */
		//1.
		String oid = request.getParameter("oid");
		//2.
		try {
			orderService.confirm(oid);
			request.setAttribute("msg", "���ȷ���ջ������׳ɹ���");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return "f:/jsps/msg.jsp";
	}
	
	/**
	 * ֧��֮ȥ����
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * https://www.yeepay.com/app-merchant-proxy/node
		 * 1.׼���ױ�����Ҫ��13+1������
		 * 2.�����ױ�����ַ��13+1������
		 * 3.�ض����ױ�
		 */
		//1.
		//�õ������ļ���������p1_MerId��������ԿkeyValue��ֵ
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(in);
		
		String p0_Cmd = "Buy";//ҵ������,�̶�ֵ��Buy��.
		String p1_MerId = props.getProperty("p1_MerId");//���ֵ�����������ļ����̻����.�̻����ױ�֧��ϵͳ��Ψһ��ݱ�ʶ.��ȡ��ʽ������λ���̻���š�
		String p2_Order = request.getParameter("oid");//�̻�������
		String p3_Amt = "0.01";//֧�����.ʵ������ͨ��oid��ѯorder���õ�order.count
		String p4_Cur = "CNY";//���ױ��֣��̶�Ϊ��CNY��
		String p5_Pid = "";//��Ʒ����,���ﲻ��
		String p6_Pcat = "";//��Ʒ����,���ﲻ��
		String p7_Pdesc = "";//��Ʒ����,���ﲻ��
		String p8_Url = props.getProperty("p8_Url");//�̻�����֧���ɹ����ݵĵ�ַ��ע�⣺�粻��p8_Url�Ĳ���ֵ֧���ɹ��������ò���֧���ɹ���֪ͨ��
		String p9_SAF = "";//�ͻ���ַ�����ﲻ��ֵ��Ϊ��1��: ��Ҫ�û����ͻ���ַ�����ױ�֧��ϵͳ;Ϊ��0��: ����Ҫ��Ĭ��Ϊ ��0��.
		String pa_MP = "";//�̻���չ��Ϣ������ֵ
		String pd_FrpId = request.getParameter("pd_FrpId");//֧��ͨ�����룬�ӱ���
		String pr_NeedResponse = "1";//Ӧ����ƣ��̶�ֵΪ1
		/*
		 * ����hmac��13������+keyValue��ͨ��PayUtils
		 */
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur,
				p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId,
				pr_NeedResponse, keyValue);//ǩ������
		
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
		//3.�ض����ױ�
		response.sendRedirect(url.toString());

		return null;
	}
	
	/**
	 * ����������ױ��ص������������жϵ��ñ��������ǲ����ױ�
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String back(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1.��ȡ11+1����
		 * 2.У������ߵ�����Ƿ�Ϊ�ױ�(hmac.�ױ����ص�hmac��11������+�Լ���Կ���ɵġ�����ͨ�����11��������ͨ���Լ�����Կ����hmac
		 * 	�����ͬ������֤Ϊ���������ױ�)
		 * 3.�鿴����״̬�Ƿ�Ϊ1�����Ϊ1���޸�Ϊ2�����ӻ��֣����������ҵ�񣩡��������1�����ݿ�ʲô�����޸ġ�
		 * 4.�жϻص���ʽ��1���ض���,2����Ե㣩
		 * 	> ����ǵ�Ե㣬����SUCCESS�ַ���,�����ױ���һֱ�ط����׳ɹ���Ϣ��
		 * 5.����ɹ���Ϣת����msg.jsp
		 */
		
		/*
		 * 1.��ȡ11+1������
		 */
		String p1_MerId = request.getParameter("p1_MerId");//�̻����
		String r0_Cmd = request.getParameter("r0_Cmd");//ҵ������,�̶�ΪBUY
		String r1_Code = request.getParameter("r1_Code");//֧��������̶�Ϊ1��ʾ���׳ɹ�
		String r2_TrxId = request.getParameter("r2_TrxId");//�ױ�֧��������ˮ��
		String r3_Amt = request.getParameter("r3_Amt");//֧�����
		String r4_Cur = request.getParameter("r4_Cur");//���ױ���
		String r5_Pid = request.getParameter("r5_Pid");//��Ʒ����
		String r6_Order = request.getParameter("r6_Order");//�̻�������,����oid����������
		String r7_Uid = request.getParameter("r7_Uid");//�ױ�֧����ԱID
		String r8_MP = request.getParameter("r8_MP");//�̻���չ��Ϣ
		String r9_BType = request.getParameter("r9_BType");//���׽����������Ϊ��1��: ������ض���;Ϊ��2��: ��������Ե�ͨѶ
		String hmac = request.getParameter("hmac");
	
		/*
		 * 2.У��������Ƿ�Ϊ�ױ�
		 */
		//�õ������ļ���������p1_MerId��������ԿkeyValue��ֵ
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		props.load(in);
		String keyValue = props.getProperty("keyValue");
		//��ר�ŵķ���У��ص�
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId,
				r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		if(!bool){//У��ʧ��
			request.setAttribute("msg", "�㲻��ʲô�ö���" );
			return "f:/jsps/msg.jsp";
		}
		//У��ɹ�
		/*
		 * 3.��ȡ����״̬��ȷ���Ƿ�Ҫ�޸Ķ���״̬�Լ���ӻ��ֵ�ҵ�������
		 */
		orderService.pay(r6_Order);//�п��ܶ����ݿ���в�����Ҳ���ܲ�����
		/*
		 * 4.�жϵ�ǰ�ص���ʽ�����Ϊ��Ե���Ҫ��success��ͷ���ַ���
		 */
		if(r9_BType.equals("1")){//������ض���
			
		} else if(r9_BType.equals("2")){//��Ե㣬������Ҫ����
			response.getWriter().print("success");
		}
		/*
		 * 5.����ɹ���Ϣ��ת����msg.jsp
		 */
		request.setAttribute("msg", "֧���ɹ����ȴ����ҷ�����");
		return "f:/jsps/msg.jsp";
	}
	
}
