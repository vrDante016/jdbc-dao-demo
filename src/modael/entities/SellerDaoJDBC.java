package modael.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DB.DB;
import DB.DbException;

public class SellerDaoJDBC implements model.dao.SellerDao{

	private Connection conn = null;
	
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}


	@Override
	public void Insert(Seller obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO seller\n"
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)\n"
					+ "VALUES\n"
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setString(3, new java.sql.Date(obj.getBirthDay().getTime()).toString());
			ps.setDouble(4, obj.getBaseSalary());
			ps.setInt(5, obj.getDepartment().getId());
			int linhasSql = ps.executeUpdate();
			if(linhasSql > 0) {
				ResultSet rt =  ps.getGeneratedKeys();
				if(rt.next()) {
					int id = rt.getInt(1);
					obj.setId(id);
				}
				else {
					throw new DbException("O funcionario não foi acrescentado");
				}
			}
			
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
			
				
	}

	@Override
	public void Update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM seller\n"
					+ "WHERE Id = ?");
			ps.setInt(1, id);
			int linhasSql = ps.executeUpdate();
			if(linhasSql == 0) {
				throw new DbException("O id não foi encontrado");
			}
		}catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
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
				Seller obj = instaciaSeller(rt,department);
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

	private Seller instaciaSeller(ResultSet rt, Department dep) throws SQLException {
			Seller obj = new Seller();
			obj.setId(rt.getInt("Id"));
			obj.setName(rt.getString("Name"));
			obj.setEmail(rt.getString("Email"));
			obj.setBaseSalary(rt.getDouble("BaseSalary"));
			obj.setBirthDay(rt.getDate("BirthDate"));
			obj.setDepartment(dep);
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
		PreparedStatement ps = null;
		ResultSet rt = null;
		
		try {
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
										+ "FROM seller INNER JOIN department "
										+ "ON seller.DepartmentId = department.Id "
										+ "ORDER BY Name ");
			rt = ps.executeQuery();
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			while (rt.next()) {
				Department dep = map.get(rt.getInt("DepartmentId"));
				if(dep == null) {
					dep = instaciaDepartamento(rt);
					map.put(rt.getInt("DepartmentId"), dep);
				}
				Seller obj = instaciaSeller(rt, dep);
				list.add(obj);
				
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rt);
		}
		
	
		}


	@Override
	public List<Seller> findByDepatment(Department department) {
		PreparedStatement ps = null;
		ResultSet rt = null;
		
		try {
			ps = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
										+ "FROM seller INNER JOIN department "
										+ "ON seller.DepartmentId = department.Id "
										+ "WHERE DepartmentId = ? "
										+ "ORDER BY Name ");
			ps.setInt(1, department.getId());
			rt = ps.executeQuery();
			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			while (rt.next()) {
				Department dep = map.get(rt.getInt("DepartmentId"));
				if(dep == null) {
					dep = instaciaDepartamento(rt);
					map.put(rt.getInt("DepartmentId"), dep);
				}
				Seller obj = instaciaSeller(rt, dep);
				list.add(obj);
				
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rt);
		}


		}
	}
	
	

