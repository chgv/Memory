package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.util.JdbcUtil;

public class EmpJdbcDAO implements I_EmpDAO {

	private static final String INSERT = "INSERT INTO EMP(emp_acc,emp_pwd,emp_name,emp_email,emp_state)VALUES(?,?,?,?,?)";
	private static final String UPDATE = "UPDATE EMP SET emp_acc=?,emp_pwd=?, emp_name=?,emp_email=?,emp_state=? WHERE emp_no=?";
	private static final String GET_ONE = "SELECT * FROM EMP WHERE emp_no=?";
	private static final String GET_ALL = "SELECT * FROM EMP";
	private static final String LOGIN = "SELECT * FROM EMP WHERE emp_acc=? and emp_pwd =?";

	static { // 資料庫連線
		try {
			Class.forName(JdbcUtil.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void insert(EmpVO empVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(INSERT)) {

			ps.setString(1, empVO.getEmp_acc());
			ps.setString(2, empVO.getEmp_pwd());
			ps.setString(3, empVO.getEmp_name());
			ps.setString(4, empVO.getEmp_email());
			ps.setBoolean(5, empVO.getEmp_state());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(EmpVO empVO) {
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL,  JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE)) {

			ps.setString(1, empVO.getEmp_acc());
			ps.setString(2, empVO.getEmp_pwd());
			ps.setString(3, empVO.getEmp_name());
			ps.setString(4, empVO.getEmp_email());
			ps.setBoolean(5, empVO.getEmp_state());
			ps.setInt(6, empVO.getEmp_no());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public EmpVO getOne(Integer emp_no) {
		ResultSet rs = null;
		EmpVO emp = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ONE)) {

			ps.setInt(1, emp_no);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new EmpVO();
				emp.setEmp_no(rs.getInt("emp_no"));
				emp.setEmp_acc(rs.getString("emp_acc"));
				emp.setEmp_pwd(rs.getString("emp_pwd"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_email(rs.getString("emp_email"));
				emp.setEmp_state(rs.getBoolean("emp_state"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return emp;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> empAll = new ArrayList<>();
		EmpVO emp = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD); // 輸入在try內會自動關閉
				PreparedStatement ps = con.prepareStatement(GET_ALL)) {
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new EmpVO();
				emp.setEmp_no(rs.getInt("emp_no"));
				emp.setEmp_pwd(rs.getString("emp_pwd"));
				emp.setEmp_acc(rs.getString("emp_acc"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_email(rs.getString("emp_email"));
				emp.setEmp_state(rs.getBoolean("emp_state"));

				empAll.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return empAll;
	}

	public EmpVO login(String emp_acc, String emp_pwd) {
		EmpVO emp = null;
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(JdbcUtil.URL, JdbcUtil.USERNAME, JdbcUtil.PASSWORD); // 輸入在try內會自動關閉
				PreparedStatement ps = con.prepareStatement(LOGIN)) {
			ps.setString(1, emp_acc);
			ps.setString(2, emp_pwd);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new EmpVO();
				emp.setEmp_no(rs.getInt("emp_no"));
				emp.setEmp_pwd(rs.getString("emp_pwd"));
				emp.setEmp_acc(rs.getString("emp_acc"));
				emp.setEmp_name(rs.getString("emp_name"));
				emp.setEmp_email(rs.getString("emp_email"));
				emp.setEmp_state(rs.getBoolean("emp_state"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return emp;
	}
}