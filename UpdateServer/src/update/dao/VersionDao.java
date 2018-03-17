package update.dao;

import java.util.List;

import update.domain.Version;

public interface VersionDao {

	void save(Version version);

	void delete(Integer id);

	void update(Version version);

	Version get(Integer id);

	List<Version> getList();

	Version findByVersion(String ver);

}