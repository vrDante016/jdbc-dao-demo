package model.dao;

import java.util.ArrayList;
import java.util.List;

import modael.entities.Seller;

public interface SellerDao {

	void Insert(Seller obj);
	void Update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
}
