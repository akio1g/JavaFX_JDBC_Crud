package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	void insert(Department client);
	void update(Department client);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findall();
}
