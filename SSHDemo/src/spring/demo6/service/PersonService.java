package spring.demo6.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public interface PersonService {

	void save();
	Set<String> getSet();
	List<String> getList();
	Properties getProps();
	Map<String,String> getMap();

}