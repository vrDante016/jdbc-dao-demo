package modael.entities;

import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements model.dao.SellerDao{

	List<Seller> seller = new ArrayList<Seller>();
	
	public SellerDaoJDBC() {
		
	}

	public SellerDaoJDBC(List<Seller> seller) {
		this.seller = seller;
	}

	@Override
	public void Insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
