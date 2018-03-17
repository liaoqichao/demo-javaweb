package update.dao;

import java.util.List;

import update.domain.UpdateFile;

public interface UpdateFileDao {

	void save(UpdateFile updateFile);

	void delete(Integer id);

	void update(UpdateFile updateFile);

	UpdateFile get(Integer id);

	List<UpdateFile> getList();

}