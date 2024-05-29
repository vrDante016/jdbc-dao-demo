package application;

import java.util.Date;

import DB.DB;
import modael.entities.Department;
import modael.entities.Seller;
import modael.entities.SellerDaoJDBC;
import model.dao.SellerDao;
import model.dao.daoFactory;

public class Programa {
	
	public static void main(String[] args) {
		
		
		SellerDao sellerDao = daoFactory.creatSellerDao();
		
		System.out.println("==== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
	}
}
