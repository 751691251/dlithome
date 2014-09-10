/**
 * 
 */
package com.dlithome.entity.dao;


import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import com.dlithome.entity.beans.CarBean;
import com.dlithome.entity.dao.inter.CarDAOInter;
import com.dlithome.utils.SimplerJDBCHelper;


/**
 * @author Jerry
 *
 */
public class CarDAO implements CarDAOInter {

	
	/**
	 * 
	 */
	public CarDAO() {
		
	}
	

	@Override
	public boolean addCar(String car, String phone) {
		CarBean c = new CarBean ();
		c.setCar(car);
		c.setPhone(phone);
		return addCar(c);
	}


	@Override
	public boolean addCar(CarBean car) {
		// TODO Auto-generated method stub
		
		String sql = "insert into DLITHOME.CAR (CAR, PHONE) values (?,?)";
		//Generate the parameters list
		ArrayList<String> paramList = new ArrayList<String>();
		//Put user_Id;
		paramList.add(car.getCar().toUpperCase());
		paramList.add(car.getPhone());
		
		
		boolean returnCd = false;
		//Define Connection and statement
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = SimplerJDBCHelper.getConn();
			pstmt = conn.prepareStatement(sql);
			//pstmt.setBinaryStream(6, image.getInputStream(),(int) image.getSize()); //to save image file to BLOB
			conn.setAutoCommit(true);
			if(SimplerJDBCHelper.executeUpdate(paramList, conn, pstmt)>0) returnCd = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SimplerJDBCHelper.close(conn, pstmt);
		}
		return returnCd;	
	}


	@Override
	public boolean deleteCar(CarBean car) {
		return deleteCar(car.getCar());
	}


	@Override
	public boolean deleteCar(String car) {
		String sql = "DELETE FROM DLITHOME.CAR WHERE CAR=?";
		ArrayList<String> paramList = new ArrayList<String>();
		paramList.add(car.toUpperCase());
		boolean returnCd = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = SimplerJDBCHelper.getConn();
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(true);
			if (SimplerJDBCHelper.executeUpdate(paramList, conn, pstmt) > 0) returnCd = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//close connections.
			SimplerJDBCHelper.close(conn, pstmt);
		}
		return returnCd;
	}


	@Override
	public CarBean find(String car) {
		// TODO Auto-generated method stub
		// Define the SQL
		String sql = "SELECT CAR, PHONE from DLITHOME.CAR WHERE CAR=? WITH UR";

		// Generate the parameters list
		ArrayList<String> paramList = new ArrayList<String>();
		paramList.add(car.toUpperCase());
		

		// Define Connection, statement and resultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		CarBean pBean = null;
		try {
			conn = SimplerJDBCHelper.getConn();
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(true);
			result = SimplerJDBCHelper.query(paramList, conn, pstmt);
			if (result != null && result.next()) {
				pBean = new CarBean();
				pBean.setCar(result.getString("CAR"));
				pBean.setPhone(result.getString("PHONE"));
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SimplerJDBCHelper.close(conn, pstmt, result);
		}
		return pBean;
	}


	@Override
	public List<CarBean> getAllCars() {
		// TODO Auto-generated method stub
		List <CarBean> cars = null;
		
		String sql = "SELECT CAR, PHONE from DLITHOME.CAR ";

		// Generate the parameters list
		ArrayList<String> paramList = new ArrayList<String>();
		
		// Define Connection, statement and resultSet
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		CarBean pBean = null;
		try {
			conn = SimplerJDBCHelper.getConn();
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(true);
			result = SimplerJDBCHelper.query(paramList, conn, pstmt);
			
			while (result != null && result.next()) {
				if (cars == null) {cars = new ArrayList();}
				pBean = new CarBean();
				pBean.setCar(result.getString("CAR"));
				pBean.setPhone(result.getString("PHONE"));
				cars.add(pBean);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			SimplerJDBCHelper.close(conn, pstmt, result);
		}
		return cars;
	}


	@Override
	public boolean updateCar(CarBean car) {
		// TODO Auto-generated method stub
		String sql = "update DLITHOME.CAR set  PHONE=? where CAR=?";
		//Generate the parameters list
		ArrayList<String> paramList = new ArrayList<String>();
		//Put user_Id;
		//paramList.add(car.getCar().toUpperCase());
		paramList.add(car.getPhone());
		paramList.add(car.getCar().toUpperCase());
		
		
		boolean returnCd = false;
		//Define Connection and statement
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = SimplerJDBCHelper.getConn();
			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(true);
			if(SimplerJDBCHelper.executeUpdate(paramList, conn, pstmt)>0)	returnCd = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SimplerJDBCHelper.close(conn, pstmt);
		}
		return returnCd;	
	}
}
