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
// 为了方便对url进行管理，可以在这里定义根路径，最终访问url是根路径+子路径
// 比如查询商品的路径就是/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {

	@Resource
	private ItemsService itemsService;
	
	// 商品查询
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsQueryVo itemsQueryVo/*封装查询条件*/) throws Exception{
		/*
		 * 包装类型参数绑定：
		 * 1. itemsQueryVo特点：复杂、多样性、无规律(用户账号、商品编号、订单信息等)
		 * 2. 页面参数和Controller形参的定义
		 * 	- 页面的参数// 
		 * 		由于ItemsMapperCustom.xml中的查询条件只有价格和商品名称。所以页面只有两个查询条件。
		 * 3. SQL语句是单表查询，所以这里不使用封装类型的参数绑定也可以。
		 */
		// itemsQueryVo == null 没有查询条件
		System.out.println(itemsQueryVo==null); // 如果参数添加@RequestParam注解，就会变成null
		List<ItemsCustom> itemsCustomList = itemsService.findItemsList(itemsQueryVo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsCustomList", itemsCustomList);
		modelAndView.setViewName("items/itemsList");
		
		return modelAndView;
		
	}
	
	// 商品信息页面修改显示，返回类型为Stirng
	// RequestMapping注解显示Http请求方式
	@RequestMapping(value="/editItems",method={RequestMethod.GET,RequestMethod.POST})
	public String editItems(Model model, @RequestParam(value="id", required=true) Integer items_id) throws Exception{
		/*
		 * 1. 参数绑定默认支持request、response、session、model/modelMap、简单类型(int、Integer、String等)
		 * 2. 参数为简单类型时，页面的参数的key要和方法形参名一致。
		 * 3. 如果使用@RequestParam注解就可以不需要页面的key和形参名一致。@RequestParam(value="id")用来指定页面的key
		 */
		
		// 1. 调用Service层方法，根据id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
//		/*
//		 * 在Contoller抛异常：
//		 * 如果查找的商品为空，抛出自定义异常CustomException
//		 */
//		if(itemsCustom == null ){
//			throw new CustomException("商品信息不存在!");
//		}
		// 2. 把模型数据添加到request域，相当于modelAndView的addObject(Str,Obj);
		model.addAttribute("items", itemsCustom);
		
		// 3. 返回视图 
		// 浏览器重定向则返回 "redirect:/items/editItems.jsp"
		return "forward:/jsps/items/editItems.jsp";
//		return "items/editItems";
	}
	
	// 返回类型为ModelAndView
//	@RequestMapping(value="/editItems",method={RequestMethod.GET})
//	public ModelAndView editItems() throws Exception{
//		
//		// 1. 调用Service层方法，根据id查询商品信息
//		ItemsCustom itemsCustom = itemsService.findItemsById(5);
//		
//		// 2. 创建modelAndView
//		ModelAndView modelAndView = new ModelAndView();
//		
//		// 3. 将商品信息添加到ModelAndView中
//		modelAndView.addObject("itemsCustom", itemsCustom);
//		
//		// 4. 设置商品修改页面。通常请求url和controller方法名和返回视图的名字一致。
//		modelAndView.setViewName("items/editItems");
//		
//		// 5. 
//		return modelAndView;
//	}
	
	// 商品信息修改提交
	/*
	 *  方法参数可以添加HttpServletRequest request。ModelAndView方式也可以。
	 *  获得request就可以得到request域内容。
	 */
	@RequestMapping(value="/editItemsSubmit")
	public String editItemsSubmit(Model model, 
			Integer id,
			@ModelAttribute("items") @Validated(value={ValidationGroup1.class,ValidationGroup2.class}) ItemsCustom itemsCustom,
			BindingResult bindingResult, 
			MultipartFile items_pic // 用来接收商品的图片，参数绑定要求如果不用注解，参数和页面的name一致，
			) throws Exception{
		
		/*
		 * 为什么全部为null
		 * 1. 上传的表单是multipart/form-data，request域获取，所为null。
		 * 	- 解决方法：注释掉页面的文件上传和修改encType为application/x-www-form-urlencoded
		 * 2. 上传的时间是字符串，分装的类型时Date类型，所以会出现类型错误。
		 * 	- 解决方法：注释掉修改时间的表单项。后面讲使用converter进行类型转换。
		 * 
		 * 输入校验
		 * 	1. 导包
		 *  2. 配置校验器并把校验器注入到HandlerAdapter中
		 *  3. 准备校验错误信息的配置文件
		 *  4. 在pojo的属性上添加注解来添加校验规则，其中message的值为校验错误信息配置文件的key(message="{key}")
		 *  5. 在Controller的方法里面，在需要校验的形参上添加@Validated注解
		 *  6. 在@Validated注解的参数后面，紧接着添加BindingResult bindingResult参数用于接收校验信息。
		 *  	- 注意：如果有多个参数需要检验，需要多个BindingResult。@Validated和BindingResult是配对出现的，
		 *  		并且BindingResult形参的顺序是固定的。
		 *  
		 *  分组校验
		 *  1. 创建一个接口(不需要实现方法)
		 *  2. 在pojo的校验注解上使用group属性，指定接口的类型，从而实现分组。
		 *  3. 在Controller的@Validated注解上使用value属性指定分组接口的类型。
		 *  
		 *  数据回显
		 *  1. 提交后，出现错误需要进行数据回显。
		 *  2. 对pojo，SpringMVC默认支持数据回显，把形参放到request域，而key是形参的简单类型(首字母小写)
		 *  3. 如果要求request域的key和回显的pojo的类型不同，可以使用@ModelAttribute("key")注解，
		 *  	value的值为request域的key。
		 *  
		 *  上传图片
		 */
//		System.out.println("id="+id);// null
//		System.out.println(itemsCustom);// 全部null
		
		// 获取校验错误信息
		if(bindingResult.hasErrors()){ //如果有校验错误
			// 输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(objectError.getCode());//校验的注解名 @Size、@NotNull
				System.out.println(objectError.getObjectName());//被校验的对象 itemsCustom
				System.out.println(new String(objectError.getDefaultMessage().getBytes("ISO-8859-1"),"UTF-8")); //打印错误信息
			}
			// 把错误信息传到页面
			model.addAttribute("allErrors", allErrors);
			
			// 不使用ModelAttribute，使用Model将pojo回显到页面
//			model.addAttribute("items", itemsCustom);
			
			return "items/editItems";
		} else{
			
			// 上传图片
			if(items_pic != null 
					&& items_pic.getOriginalFilename()!=null 
					&& !items_pic.getOriginalFilename().trim().equals("") ){
				// 物理路径
				String pic_path = "E:\\Eclipse\\pic\\"; 
				
				// 获取图片原始名称
				String originalFilename = items_pic.getOriginalFilename();
				
				/*
				 *  新的图片名称:UUID+文件扩展名
				 *  如果页面没有选择图片就提交，会显示数组越界错误，因为originalFilename一个"."都没有，无法获取子串
				 *  所以要在判断语句中添加originalFilename!=null,trim().equals("")
				 */
				String newFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				
				// 新图片
				File newFile = new File(pic_path+newFilename);
				
				// 将内存中的数据写入磁盘
				items_pic.transferTo(newFile);
				
				// 将新的图片名称写到itemsCustom的中
				itemsCustom.setPic(newFilename);
			}
			
			// 1. 调用service方法更新商品信息。页面需要将商品信息传到此方法。
			itemsService.updateItems(id, itemsCustom);
			
			// 2. 浏览器重定向,在一个controller里面，不需要加/items根路径
//		return "success";//jsps/success.jsp，视图解析器指定了前缀和后缀
			return "redirect:queryItems.action";
		}
	}
	
	//批量删除商品信息
	@RequestMapping(value="/deleteItems",method={RequestMethod.POST})
	public String deleteItems(Integer[] items_id) throws Exception{
		/*
		 * 为什么上传的类型时String类型而不是int类型？
		 * 为什么又突然可以使用Integer[]了？
		 */
//		for (String string : items_id) { 
//			System.out.println(Integer.valueOf(string));
//		}
		for (Integer integer : items_id) {
			System.out.println(integer);
		}
		// 调用Service批量删除商品
		return "forward:/jsps/items/itemsList.jsp";
	}
	
	// 批量修改商品的页面，将商品信息查询出来，返回可以编辑商品信息的页面
	@RequestMapping("/editItemsQuery")
	public String editItemsQuery(Model model) throws Exception{
		List<ItemsCustom> itemsCustomList = itemsService.findItemsList(null);
		model.addAttribute("itemsCustomList", itemsCustomList);
		return "items/editItemsQuery";
	}
	// 批量修改商品的提交
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		/*
		 * 通过itemsQueryVo的itemsCustomList属性来接收页面的批量提交数据
		 */
		for (ItemsCustom itemsCustom : itemsQueryVo.getItemsCustomList()) {
			System.out.println(itemsCustom.getName()+" - "+itemsCustom.getPrice());
			System.out.println(itemsCustom.getCreatetime()==null);
		}
		return "items/editItemsQuery";
	}
	
	// @ModelAttribute将方法的返回值
	// 页面上可以得到itemsType的数据 
	@ModelAttribute("itemsTypeMap") // 将方法的返回值放到request域中，其中key为注解指定的"itemsType"
	public Map<String,String> getItemsType() throws Exception{
		Map<String,String> itemsTypeMap = new HashMap<String,String>();
		itemsTypeMap.put("101", "数码");
		itemsTypeMap.put("102", "母婴");
		return itemsTypeMap;
	}
	
	// 查询商品信息，输出JSON
	// 根据商品id查询商品信息
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception{
		/*
		 * url模板映射
		 * {xx}：占位符
		 * 
		 * @PathVariable注解：接收url上的对应占位符的参数
		 * 
		 * 多个参数：
		 * url："/itemsView/{id}/{type}"
		 * 形参：(@PathVariable("id") Integer arg0, @PathVariable("type") Integer arg1)
		 */
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		return itemsCustom;
	}
}
