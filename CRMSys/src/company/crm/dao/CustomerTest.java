//package company.crm.dao;
//
//import org.junit.Test;
//
//import company.crm.domain.Customer;
//import lqcUtils.CommonUtils;
//
//public class CustomerTest {
//
//	@Test
//	public void test() throws Exception{
//
//		CustomerDao dao = new CustomerDao();
//		for (int i = 1; i <= 300; i++) {
//			Customer c = new Customer();
//			
//			c.setCid(CommonUtils.uuid());
//			c.setCname("customer_"+i);
//			c.setGender(i%2==0?"男":"女");
//			c.setBirthday("2015-10-31");
//			c.setCellphone("139"+i);
//			c.setEmail("customer_"+i+"@163.com");
//			c.setDescription("我是客户");
//			dao.add(c);
//		}
//	}
//}
