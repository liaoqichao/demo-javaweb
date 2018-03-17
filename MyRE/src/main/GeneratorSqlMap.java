package main;

import framework.Genarator;


public class GeneratorSqlMap {

	public static void main(String[] args) {
		try {
			GeneratorSqlMap generatorSqlMap = new GeneratorSqlMap();
			generatorSqlMap.generator(); // 执行生成代码
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generator() throws Exception{
		Genarator genarator = new Genarator();
		// 包，实体类名字，表名
		genarator.genarator("test.com.zjft.part.business.express.reim", "ExpressPriceReimMain", "PART_EXPRESS_REIM_M");
	}

}
