package model.dao;

import java.util.ArrayList;
import java.util.List;

import modael.entities.Department;

public interface DepartmentDao {

	void Insert(Department obj);
	void Update(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();
}
