package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modael.entities.Department;
import model.dao.DepartmentDao;
import model.dao.daoFactory;

public class programa2 {
	
	public static void main(String[] args) {
		
		Scanner ler = new Scanner(System.in);
		DepartmentDao departmentDao = daoFactory.creatDepartmentDao();
		
		System.out.println("===== TEST 1: department FindById ====");
		Department department = departmentDao.findById(2);
		System.out.println(department);
		

		
		System.out.println("===== TEST 2: FindALL ====");
		List<Department> list = new ArrayList<Department>();
		list = departmentDao.findAll();
		for(Department dep : list) {
			System.out.println(dep);
		}
		
		System.out.println("===== TEST 3: department Insert ====");
		departmentDao.Insert(new Department(9, "comedor de casadas"));;
		System.out.println("Funcionario acrescentado com sucessoS");
		
		System.out.println("===== TEST 4: department Delete ====");
		System.out.println("Entre com o id que deseja remover");
		int id = ler.nextInt();
		departmentDao.deleteById(id);;
		System.out.println("Funcionario acrescentado com sucessoS");
		

		System.out.println("==== TEST 6: seller Department ====");
		System.out.println("Entre com id");
		int id2 = ler.nextInt();
		department = departmentDao.findById(id2);
		department.setName("Martha");
		departmentDao.Update(department);
		
		
	}
}
