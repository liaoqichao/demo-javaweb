package company.crm.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.crm.domain.Customer;
import company.crm.domain.PageBean;
import company.crm.service.CustomerService;
import lqcUtils.CommonUtils;
import lqcUtils.servlet.BaseServlet;

/**
 * web��
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//����CustomerService
	private CustomerService customerService = new CustomerService();
	
	/**
	 * ��ӿͻ�
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//1.��װ������
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		//2.��ȫ��Ϣ:cid��ʹ��UUID
		c.setCid(CommonUtils.uuid());
		//3.ʹ��service�����ӹ���
		customerService.add(c);
		//4.��request���б���ɹ���Ϣ
		request.setAttribute("msg", "��ϲ,��ӿͻ��ɹ���");
		//5.ת����msg.jsp(ͨ��BaseServlet)
		return "f:/msg.jsp";
	}
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		û���÷�ҳ
//		//1.����service����
//		List<Customer> list = customerService.findAll();
//		//2.�ѽ�����浽request����
//		request.setAttribute("customers", list);
//		//3.ת����list.jsp
//		return "f:/list.jsp";
		
//		ʹ�÷�ҳ
		//1.��ȡҳ�洫�ݵ�pageCode,pc�����pc�����ڣ�pc=1�����pc����ת��Ϊint���ͼ���
		int pageCode = getPageCode(request);
		//2.����pageSize,ps��ÿҳ10�м�¼
		int pageSize = 10;
		//3.ʹ��pc��psʹ��service���õ�pageBean
		PageBean<Customer> pb = customerService.findAll(pageCode,pageSize);//����pc��ps��service���õ�pageBean
		//4.����URL
		pb.setUrl(getUrl(request));
		//5.���浽request��
		request.setAttribute("pb", pb);
		//6.ת����list.jsp
		return "f:/list.jsp";
	}
	
	/**
	 * ��ȡpageCode,pc
	 */
	private int getPageCode(HttpServletRequest request){
		
		String  value = request.getParameter("pc");
		if(value == null || value.trim().isEmpty()){
			return 1;
		} else{
			return Integer.parseInt(value);
		}
	}
	
	/**
	 * ���أ���������ѯ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String beforeEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.��ȡ����cid
		String cid = request.getParameter("cid");
		//2.����service��ѯ������ͨ��cid�õ�Customer����
		Customer c = customerService.load(cid);
		//3.���浽request��
		request.setAttribute("customer", c);
		//4.ת����edit.jsp
		return "f:/edit.jsp";
	}
	
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.��װ�����ݵ�Customer�����С�request�����Ѿ���cid�����Կ���ֱ�ӷ�װ
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		//2.����service��ɱ༭
		customerService.edit(c);
		//3.����ɹ���Ϣ��request��
		request.setAttribute("msg", "��ϲ,�޸ĳɹ�");
		//4.ת����msg.jsp��ʾ�ɹ�
		return "f:/msg.jsp";
	}
	
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.��ȡrequest���е�cid
		String cid = request.getParameter("cid");
		//2.����service���ɾ��
		customerService.delete(cid);
		//3.����ɹ���Ϣ��request��
		request.setAttribute("msg", "��ϲ,ɾ���ɹ�");
		//4.ת����msg.jsp��ʾ�ɹ�
		return "f:/msg.jsp";
	}
	
	/**
	 * ��������ϲ�ѯ
	 */
	public String query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ûʹ�÷�ҳ
//		//1.��װ�����ݵ�Customer,ֻ��4�����ԣ�cname,gender,cellphone,email
//		//�Ժ�hibernate��Criteria�ӿڡ�QBC query by criteria ����������ѯ
//		Customer criteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
//		//2.����service�����������ݣ�����list
//		List<Customer> list = customerService.query(criteria);
//		//3.��list���浽request����
//		request.setAttribute("customers", list);
//		//4.ת����list.jsp
//		return "f:/list.jsp";
		
		/**
		 * ��������ѯ��δ����ҳ��
		 * 1.ͬһ���󡣰�query.list�ı���ΪGET���󣬺���URL����ʾ�����������������Ļ��Զ�����URL����
		 * 2.��URL�������������浽pageBean��URL�У�����pc��Ҫ��pc��list.jsp�Լ����
		 * 3.��ҳ�ĳ����Ӷ�${pb.url}&pc=${i}��ȡ������·���������url�����˵�һ���ύ������������
		 */
//		ʹ�÷�ҳ
//		System.out.println(getUrl(request));
		//0.��������װ��Customer������
		Customer criteria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		/*
		 * ����������⡣GET����ֻ���ֶ�����Ϊʲô���ô��������˷�������BaseServletû�д���
		 */
//		criteria = encoding(criteria);
		
		//1.�õ�pc
		int pc = getPageCode(request);
		
		//2.����ps
		int ps = 10;
		
		//3.ʹ��pc��ps����������criteria����service�����õ�pageBean
		PageBean<Customer> pb = customerService.query(pc,ps,criteria);
		
		//4.�õ�URL���浽pb��
		pb.setUrl(getUrl(request));
		
		//5.��pageBean���浽request����
		request.setAttribute("pb", pb);
		
		//6.ת����list.jsp
		return "f:/list.jsp";
	}
	
	/**
	 * GET����Ϊʲô���ô���������⣿�����˷������ˡ�һ��ʼ����UTF-8
	 * ����߼��������ܵ�4������
	 * @param criteria
	 * @return
	 */
	@SuppressWarnings("unused")
	private Customer encoding(Customer criteria) {
		
		try{
			String cname = criteria.getCname();
			String gender = criteria.getGender();
			String cellphone = criteria.getCellphone();
			String email = criteria.getEmail();
			
			if(cname != null && !cname.trim().isEmpty()){
				cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
				criteria.setCname(cname);
			}
			if(gender != null && !gender.trim().isEmpty()){
				System.out.println(gender);
//				gender = new String(gender.getBytes("ISO-8859-1"),"UTF-8");
				System.out.println(gender);
				criteria.setGender(gender);
			}
			if(cellphone != null && !cellphone.trim().isEmpty()){
				cellphone = new String(cellphone.getBytes("ISO-8859-1"),"UTF-8");
				criteria.setCellphone(cellphone);
			}
			if(email != null && !email.trim().isEmpty()){
				email = new String(email.getBytes("ISO-8859-1"),"UTF-8");
				criteria.setEmail(email);
			}
			
			return criteria;
		} catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * ��ȡurl
	 * 	/��Ŀ��/Servlet·��?�����ַ���
	 * @param request
	 * @return
	 */
	private String getUrl(HttpServletRequest request){
		String contextPath = request.getContextPath();//��ȡ��Ŀ��
		String servletPath = request.getServletPath();//��ȡServlet·��
		String queryString = request.getQueryString();//��ȡ�ʺź�Ĳ���
		
		//queryString�п��ܴ���pc��������Ҫpc����ҳ��list.jsp���ƾͺá�
		if(queryString.contains("&pc=")){
			int index = queryString.lastIndexOf("&pc=");
			queryString = queryString.substring(0, index);
		}
		return contextPath + servletPath + "?" + queryString;
	}
}
