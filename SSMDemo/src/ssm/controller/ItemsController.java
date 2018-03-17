package ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ssm.controller.validation.ValidationGroup1;
import ssm.controller.validation.ValidationGroup2;
import ssm.po.ItemsCustom;
import ssm.po.ItemsQueryVo;
import ssm.service.ItemsService;

@Controller
// Ϊ�˷����url���й������������ﶨ���·�������շ���url�Ǹ�·��+��·��
// �����ѯ��Ʒ��·������/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {

	@Resource
	private ItemsService itemsService;
	
	// ��Ʒ��ѯ
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsQueryVo itemsQueryVo/*��װ��ѯ����*/) throws Exception{
		/*
		 * ��װ���Ͳ����󶨣�
		 * 1. itemsQueryVo�ص㣺���ӡ������ԡ��޹���(�û��˺š���Ʒ��š�������Ϣ��)
		 * 2. ҳ�������Controller�βεĶ���
		 * 	- ҳ��Ĳ���// 
		 * 		����ItemsMapperCustom.xml�еĲ�ѯ����ֻ�м۸����Ʒ���ơ�����ҳ��ֻ��������ѯ������
		 * 3. SQL����ǵ����ѯ���������ﲻʹ�÷�װ���͵Ĳ�����Ҳ���ԡ�
		 */
		// itemsQueryVo == null û�в�ѯ����
		System.out.println(itemsQueryVo==null); // ����������@RequestParamע�⣬�ͻ���null
		List<ItemsCustom> itemsCustomList = itemsService.findItemsList(itemsQueryVo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsCustomList", itemsCustomList);
		modelAndView.setViewName("items/itemsList");
		
		return modelAndView;
		
	}
	
	// ��Ʒ��Ϣҳ���޸���ʾ����������ΪStirng
	// RequestMappingע����ʾHttp����ʽ
	@RequestMapping(value="/editItems",method={RequestMethod.GET,RequestMethod.POST})
	public String editItems(Model model, @RequestParam(value="id", required=true) Integer items_id) throws Exception{
		/*
		 * 1. ������Ĭ��֧��request��response��session��model/modelMap��������(int��Integer��String��)
		 * 2. ����Ϊ������ʱ��ҳ��Ĳ�����keyҪ�ͷ����β���һ�¡�
		 * 3. ���ʹ��@RequestParamע��Ϳ��Բ���Ҫҳ���key���β���һ�¡�@RequestParam(value="id")����ָ��ҳ���key
		 */
		
		// 1. ����Service�㷽��������id��ѯ��Ʒ��Ϣ
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
//		/*
//		 * ��Contoller���쳣��
//		 * ������ҵ���ƷΪ�գ��׳��Զ����쳣CustomException
//		 */
//		if(itemsCustom == null ){
//			throw new CustomException("��Ʒ��Ϣ������!");
//		}
		// 2. ��ģ��������ӵ�request���൱��modelAndView��addObject(Str,Obj);
		model.addAttribute("items", itemsCustom);
		
		// 3. ������ͼ 
		// ������ض����򷵻� "redirect:/items/editItems.jsp"
		return "forward:/jsps/items/editItems.jsp";
//		return "items/editItems";
	}
	
	// ��������ΪModelAndView
//	@RequestMapping(value="/editItems",method={RequestMethod.GET})
//	public ModelAndView editItems() throws Exception{
//		
//		// 1. ����Service�㷽��������id��ѯ��Ʒ��Ϣ
//		ItemsCustom itemsCustom = itemsService.findItemsById(5);
//		
//		// 2. ����modelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		
//		// 3. ����Ʒ��Ϣ��ӵ�ModelAndView��
//		modelAndView.addObject("itemsCustom", itemsCustom);
//		
//		// 4. ������Ʒ�޸�ҳ�档ͨ������url��controller�������ͷ�����ͼ������һ�¡�
//		modelAndView.setViewName("items/editItems");
//		
//		// 5. 
//		return modelAndView;
//	}
	
	// ��Ʒ��Ϣ�޸��ύ
	/*
	 *  ���������������HttpServletRequest request��ModelAndView��ʽҲ���ԡ�
	 *  ���request�Ϳ��Եõ�request�����ݡ�
	 */
	@RequestMapping(value="/editItemsSubmit")
	public String editItemsSubmit(Model model, 
			Integer id,
			@ModelAttribute("items") @Validated(value={ValidationGroup1.class,ValidationGroup2.class}) ItemsCustom itemsCustom,
			BindingResult bindingResult, 
			MultipartFile items_pic // ����������Ʒ��ͼƬ��������Ҫ���������ע�⣬������ҳ���nameһ�£�
			) throws Exception{
		
		/*
		 * Ϊʲôȫ��Ϊnull
		 * 1. �ϴ��ı���multipart/form-data��request���ȡ����Ϊnull��
		 * 	- ���������ע�͵�ҳ����ļ��ϴ����޸�encTypeΪapplication/x-www-form-urlencoded
		 * 2. �ϴ���ʱ�����ַ�������װ������ʱDate���ͣ����Ի�������ʹ���
		 * 	- ���������ע�͵��޸�ʱ��ı�����潲ʹ��converter��������ת����
		 * 
		 * ����У��
		 * 	1. ����
		 *  2. ����У��������У����ע�뵽HandlerAdapter��
		 *  3. ׼��У�������Ϣ�������ļ�
		 *  4. ��pojo�����������ע�������У���������message��ֵΪУ�������Ϣ�����ļ���key(message="{key}")
		 *  5. ��Controller�ķ������棬����ҪУ����β������@Validatedע��
		 *  6. ��@Validatedע��Ĳ������棬���������BindingResult bindingResult�������ڽ���У����Ϣ��
		 *  	- ע�⣺����ж��������Ҫ���飬��Ҫ���BindingResult��@Validated��BindingResult����Գ��ֵģ�
		 *  		����BindingResult�βε�˳���ǹ̶��ġ�
		 *  
		 *  ����У��
		 *  1. ����һ���ӿ�(����Ҫʵ�ַ���)
		 *  2. ��pojo��У��ע����ʹ��group���ԣ�ָ���ӿڵ����ͣ��Ӷ�ʵ�ַ��顣
		 *  3. ��Controller��@Validatedע����ʹ��value����ָ������ӿڵ����͡�
		 *  
		 *  ���ݻ���
		 *  1. �ύ�󣬳��ִ�����Ҫ�������ݻ��ԡ�
		 *  2. ��pojo��SpringMVCĬ��֧�����ݻ��ԣ����βηŵ�request�򣬶�key���βεļ�����(����ĸСд)
		 *  3. ���Ҫ��request���key�ͻ��Ե�pojo�����Ͳ�ͬ������ʹ��@ModelAttribute("key")ע�⣬
		 *  	value��ֵΪrequest���key��
		 *  
		 *  �ϴ�ͼƬ
		 */
//		System.out.println("id="+id);// null
//		System.out.println(itemsCustom);// ȫ��null
		
		// ��ȡУ�������Ϣ
		if(bindingResult.hasErrors()){ //�����У�����
			// ���������Ϣ
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(objectError.getCode());//У���ע���� @Size��@NotNull
				System.out.println(objectError.getObjectName());//��У��Ķ��� itemsCustom
				System.out.println(new String(objectError.getDefaultMessage().getBytes("ISO-8859-1"),"UTF-8")); //��ӡ������Ϣ
			}
			// �Ѵ�����Ϣ����ҳ��
			model.addAttribute("allErrors", allErrors);
			
			// ��ʹ��ModelAttribute��ʹ��Model��pojo���Ե�ҳ��
//			model.addAttribute("items", itemsCustom);
			
			return "items/editItems";
		} else{
			
			// �ϴ�ͼƬ
			if(items_pic != null 
					&& items_pic.getOriginalFilename()!=null 
					&& !items_pic.getOriginalFilename().trim().equals("") ){
				// ����·��
				String pic_path = "E:\\Eclipse\\pic\\"; 
				
				// ��ȡͼƬԭʼ����
				String originalFilename = items_pic.getOriginalFilename();
				
				/*
				 *  �µ�ͼƬ����:UUID+�ļ���չ��
				 *  ���ҳ��û��ѡ��ͼƬ���ύ������ʾ����Խ�������ΪoriginalFilenameһ��"."��û�У��޷���ȡ�Ӵ�
				 *  ����Ҫ���ж���������originalFilename!=null,trim().equals("")
				 */
				String newFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				
				// ��ͼƬ
				File newFile = new File(pic_path+newFilename);
				
				// ���ڴ��е�����д�����
				items_pic.transferTo(newFile);
				
				// ���µ�ͼƬ����д��itemsCustom����
				itemsCustom.setPic(newFilename);
			}
			
			// 1. ����service����������Ʒ��Ϣ��ҳ����Ҫ����Ʒ��Ϣ�����˷�����
			itemsService.updateItems(id, itemsCustom);
			
			// 2. ������ض���,��һ��controller���棬����Ҫ��/items��·��
//		return "success";//jsps/success.jsp����ͼ������ָ����ǰ׺�ͺ�׺
			return "redirect:queryItems.action";
		}
	}
	
	//����ɾ����Ʒ��Ϣ
	@RequestMapping(value="/deleteItems",method={RequestMethod.POST})
	public String deleteItems(Integer[] items_id) throws Exception{
		/*
		 * Ϊʲô�ϴ�������ʱString���Ͷ�����int���ͣ�
		 * Ϊʲô��ͻȻ����ʹ��Integer[]�ˣ�
		 */
//		for (String string : items_id) { 
//			System.out.println(Integer.valueOf(string));
//		}
		for (Integer integer : items_id) {
			System.out.println(integer);
		}
		// ����Service����ɾ����Ʒ
		return "forward:/jsps/items/itemsList.jsp";
	}
	
	// �����޸���Ʒ��ҳ�棬����Ʒ��Ϣ��ѯ���������ؿ��Ա༭��Ʒ��Ϣ��ҳ��
	@RequestMapping("/editItemsQuery")
	public String editItemsQuery(Model model) throws Exception{
		List<ItemsCustom> itemsCustomList = itemsService.findItemsList(null);
		model.addAttribute("itemsCustomList", itemsCustomList);
		return "items/editItemsQuery";
	}
	// �����޸���Ʒ���ύ
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		/*
		 * ͨ��itemsQueryVo��itemsCustomList����������ҳ��������ύ����
		 */
		for (ItemsCustom itemsCustom : itemsQueryVo.getItemsCustomList()) {
			System.out.println(itemsCustom.getName()+" - "+itemsCustom.getPrice());
			System.out.println(itemsCustom.getCreatetime()==null);
		}
		return "items/editItemsQuery";
	}
	
	// @ModelAttribute�������ķ���ֵ
	// ҳ���Ͽ��Եõ�itemsType������ 
	@ModelAttribute("itemsTypeMap") // �������ķ���ֵ�ŵ�request���У�����keyΪע��ָ����"itemsType"
	public Map<String,String> getItemsType() throws Exception{
		Map<String,String> itemsTypeMap = new HashMap<String,String>();
		itemsTypeMap.put("101", "����");
		itemsTypeMap.put("102", "ĸӤ");
		return itemsTypeMap;
	}
	
	// ��ѯ��Ʒ��Ϣ�����JSON
	// ������Ʒid��ѯ��Ʒ��Ϣ
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception{
		/*
		 * urlģ��ӳ��
		 * {xx}��ռλ��
		 * 
		 * @PathVariableע�⣺����url�ϵĶ�Ӧռλ���Ĳ���
		 * 
		 * ���������
		 * url��"/itemsView/{id}/{type}"
		 * �βΣ�(@PathVariable("id") Integer arg0, @PathVariable("type") Integer arg1)
		 */
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		return itemsCustom;
	}
}
