package modael.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.DB;
import DB.DbException;
import model.dao.DepartmentDao;

public class DepartmentJDBC implements DepartmentDao{

	private Connection conn;
	public DepartmentJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void Insert(Department obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO department\n"
					+ "(Id, Name)\n"
					+ "VALUES\n"
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getName());
			int colunasSQl = ps.executeUpdate();
			if(colunasSQl > 0) {
				ResultSet rt = ps.getGeneratedKeys();
				if(rt.next()) {
					int id = rt.getInt(1);
					obj.setId(id);
				}
			}else {
				throw new DbException("O departamento não foi acrescentado");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void Update(Department obj) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE department\n"
					+ "SET Name = ?\n"
					+ "WHERE Id = ?");
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
		    DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM department WHERE id = ?");
			ps.setInt(1, id);
			int colunasSQL = ps.executeUpdate();
			if(colunasSQL == 0) {
				throw new DbException("O departamento não foi encontrado");
			}
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rt = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
			ps.setInt(1, id);
			rt = ps.executeQuery();
			if(rt.next()) {
				Department dep = new Department();
				dep = instaciaDepartamento(rt);
				return dep;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rt);
		}
	}
	private Department instaciaDepartamento(ResultSet rt) throws SQLException {
		Department dep = new Department();
		dep.setId(rt.getInt("Id"));
		dep.setName(rt.getString("Name"));
		return dep;
		}

	@Override
	public List<Department> findAll() {
		PreparedStatement ps = null;
		ResultSet rt = null;
		try {
			ps = conn.prepareStatement("SELECT * FROM department WHERE Id");
			rt = ps.executeQuery();
			List<Department> list = new ArrayList<Department>();
			while(rt.next()) {
				Department dep = new Department();
				dep = instaciaDepartamento(rt);
				list.add(dep);
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


