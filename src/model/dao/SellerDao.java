package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
	void insert(Seller client);
	void update(Seller client);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findall();
	List<Seller> findByDepartment(Department department);
}
