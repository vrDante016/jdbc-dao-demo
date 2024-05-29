package model.dao;

import DB.DB;
import modael.entities.SellerDaoJDBC;

public class daoFactory {

	public static SellerDao creatSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
}
