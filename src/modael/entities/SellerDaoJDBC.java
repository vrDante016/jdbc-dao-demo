package modael.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DB.DB;
import DB.DbException;

public class SellerDaoJDBC implements model.dao.SellerDao{

	private Connection conn = null;
	
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
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
		PreparedStatement st = null;
		ResultSet rt = null;
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?"
					);
			st.setInt(1, id);
			rt = st.executeQuery();
			if(rt.next()) {
				Department department = instaciaDepartamento(rt);
				Seller obj = instaciaSeller(rt);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rt);
		}
		
		
	}

	private Seller instaciaSeller(ResultSet rt) throws SQLException {
			Seller obj = new Seller();
			obj.setId(rt.getInt("Id"));
			obj.setName(rt.getString("Name"));
			obj.setEmail(rt.getString("Email"));
			obj.setBaseSalary(rt.getDouble("BaseSalary"));
			obj.setBirthDay(rt.getDate("BirthDate"));
			obj.setDepartment(instaciaDepartamento(rt));
			return obj;
		
	}


	private Department instaciaDepartamento(ResultSet rt) throws SQLException {
		Department dep = new Department();
		dep.setId(rt.getInt("DepartmentId"));
		dep.setName(rt.getString("DepName"));
		return dep;
		}


	@Override
	public List<Seller> findAll() {
		return null;
		
	
		}
		
	
	}
	
	

