package com.dlithome.entity.dao;

import java.util.ArrayList;
import java.util.List;

import com.dlithome.entity.beans.CarBean;
import com.dlithome.entity.dao.inter.CarDAOInter;
import com.dlithome.utils.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class CarMongoDao implements CarDAOInter {
	
	private DBCollection cars;

	public CarMongoDao() {
		super();
		cars = SimpleMongoDBHelper.getDB("dlithome").getCollection("cars");
	}

	@Override
	public boolean addCar(String car, String phone) {
		BasicDBObject document = new BasicDBObject();
		document.put("car", car);
		document.put("phone", phone);
		cars.insert(document);
		return true;
	}

	@Override
	public boolean addCar(CarBean car) {
		return addCar(car.getCar(), car.getPhone());
	}

	@Override
	public boolean deleteCar(CarBean car) {
		return deleteCar(car.getCar());
	}

	@Override
	public boolean deleteCar(String car) {
		BasicDBObject document = new BasicDBObject();
		document.put("car", car);
		cars.remove(document);
		return true;
	}

	@Override
	public CarBean find(String car) {
		
		CarBean b = null;
		BasicDBObject fields = new BasicDBObject();
		fields.put("car", car);
	 
		DBCursor cursor = cars.find(fields);
		if (cursor.hasNext()) {
			b = new CarBean();
			b.setCar(car);
			
			DBObject c = cursor.next();
			b.setPhone((String) c.get("phone"));
		}
		return null;
	}

	@Override
	public List<CarBean> getAllCars() {
		
		List<CarBean> bs = new ArrayList<CarBean> ();
		DBCursor cursor = cars.find();
		while (cursor.hasNext()) {
			CarBean b = new CarBean ();
			DBObject c = cursor.next();
			b.setCar((String) c.get("car"));
			b.setPhone((String) c.get("phone"));
			bs.add(b);
		}
		return bs;
	}

	@Override
	public boolean updateCar(CarBean car) {
		BasicDBObject user = new BasicDBObject();
		user.append("$set", new BasicDBObject().append("car", car.getCar()).append("phone", car.getPhone()));
		
		BasicDBObject s = new BasicDBObject();
		s.append("car", car);
		cars.update(s, user, true, false);
		return false;
	}

}
