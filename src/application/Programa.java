package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import DB.DB;
import modael.entities.Department;
import modael.entities.Seller;
import modael.entities.SellerDaoJDBC;
import model.dao.SellerDao;
import model.dao.daoFactory;

public class Programa {
	
	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		
		SellerDao sellerDao = daoFactory.creatSellerDao();
		
		System.out.println("==== TEST 1: seller findById ====");
		Seller seller = sellerDao.findById(1);
		System.out.println(seller);
		
		System.out.println("==== TEST 2: seller Department ====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepatment(department);
		for(Seller obj: list) {
			System.out.println(obj);
		}
		System.out.println("==== TEST 3: seller Department ====");
		list = sellerDao.findAll();
		for(Seller obj: list) {
			System.out.println(obj);
		}
		System.out.println("==== TEST 4: seller Department ====");
		sellerDao.Insert(new Seller(1,"rafael", "rafael@gmail.com", new Date(),2000.00 ,department));
		list = sellerDao.findAll();
		for(Seller obj: list) {
			System.out.println(obj);
		
		}
		System.out.println("==== TEST 5: seller delete ====");
		System.out.println("Entre com o id que deseja remover!");
		Integer id = ler.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Funcioanrio removido");
		
		System.out.println("==== TEST 6: seller Department ====");
		System.out.println("Entre com id");
		int id2 = ler.nextInt();
		seller = sellerDao.findById(id2);
		seller.setName("Martha");
		sellerDao.Update(seller);
		
		
	}
	
}
