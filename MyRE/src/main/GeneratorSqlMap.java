package main;

import framework.Genarator;


public class GeneratorSqlMap {

	public static void main(String[] args) {
		try {
			GeneratorSqlMap generatorSqlMap = new GeneratorSqlMap();
			generatorSqlMap.generator(); // ִ�����ɴ���
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generator() throws Exception{
		Genarator genarator = new Genarator();
		// ����ʵ�������֣�����
		genarator.genarator("test.com.zjft.part.business.express.reim", "ExpressPriceReimMain", "PART_EXPRESS_REIM_M");
	}

}
