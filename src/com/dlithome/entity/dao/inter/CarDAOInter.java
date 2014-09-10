package com.dlithome.entity.dao.inter;

import com.dlithome.entity.beans.*;
import java.util.*;

public interface CarDAOInter {
	public boolean addCar(String car, String phone);
	public boolean addCar(CarBean car);
	public boolean deleteCar (CarBean car);
	public boolean deleteCar (String car);
	public CarBean find (String car);
	public List<CarBean> getAllCars ();
	public boolean updateCar (CarBean car);
}
