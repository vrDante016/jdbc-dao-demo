package model.dao;

import modael.entities.SellerDaoJDBC;

public class daoFactory {

	public static SellerDao creatSellerDao() {
		return new SellerDaoJDBC();
	}
}
