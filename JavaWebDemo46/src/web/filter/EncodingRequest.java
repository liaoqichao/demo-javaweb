package web.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * װ��request,��ǿgetParameter������������������洦���������
 * @author Administrator
 *
 */
public class EncodingRequest extends HttpServletRequestWrapper{
	@SuppressWarnings("unused")
	private HttpServletRequest req;
	
	public EncodingRequest(HttpServletRequest request){
		super(request);
		this.req = request;
	}

	// Ȼ��GET�����ȻĬ����UTF-8���롣
//	@Override
//	public String getParameter(String name) {
//		// TODO Auto-generated method stub
//		try {
//			return new String(super.getParameter(name).getBytes("ISO-8859-1"),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
//	}

//	@Override
//	public Map<String, String[]> getParameterMap() {
//		// TODO Auto-generated method stub
//		try {
//			Map<String, String[]> pMap = super.getParameterMap();
//			for (String key : pMap.keySet()) {
//				String[] values = pMap.get(key);
//				for(int i = 0 ; i < values.length ; i++){
//						values[i] = new String(values[i].getBytes("ISO-8859-1"),"UTF-8");
//				}
//				pMap.put(key, values);
//			}
//			return pMap;
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
//	}

//	@Override
//	public String[] getParameterValues(String name) {
//		// TODO Auto-generated method stub
//		try {
//			String[] values = super.getParameterValues(name);
//			for(int i = 0 ; i < values.length ; i++){
//					values[i] = new String(values[i].getBytes("UTF-8"),"UTF-8");
//			}
//			return values;
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
//	}

	

	
}
