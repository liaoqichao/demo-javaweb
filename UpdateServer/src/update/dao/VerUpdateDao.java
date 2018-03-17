package update.dao;

import java.util.List;

import update.domain.VerUpdate;

public interface VerUpdateDao {

	/**
	 * 增加更新信息
	 * @param verUpdate
	 */
	void save(VerUpdate verUpdate);

	/**
	 * 根据主键删除更新信息
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * 修改更新信息
	 * @param verUpdate
	 */
	void update(VerUpdate verUpdate);

	/**
	 * 根据主键查询更新信息
	 * @param id
	 * @return
	 */
	VerUpdate get(Integer id);

	/**
	 * 查询数据库中所有的更新信息
	 * @return
	 */
	List<VerUpdate> getList();

	/**
	 * 根据版本查询该版本更新信息
	 * @param version
	 * @return
	 */
	public VerUpdate findByVersion(String version);
}