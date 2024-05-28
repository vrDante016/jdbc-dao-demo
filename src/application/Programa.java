package application;

import java.util.Date;

import modael.entities.Department;
import modael.entities.Seller;

public class Programa {
	
	public static void main(String[] args) {
		
		Department obj = new Department(1, "Livros");
		Seller seller = new Seller(2, "Bundovisk", "bundovisk@gmail.com", new Date(), 2000.0, obj);
		System.out.println(seller);
	}
}
