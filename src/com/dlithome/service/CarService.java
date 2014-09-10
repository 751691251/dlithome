/**
 * 
 */
package com.dlithome.service;

import com.dlithome.entity.dao.*;
import java.util.regex.*;  
import java.util.*;
import com.dlithome.entity.beans.*;
/**
 * @author Jerry
 *
 */
public class CarService implements ServiceInterface {

	/**
	 * 
	 */
	public CarService() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.dlithome.service.ServiceInterface#processRequest(java.lang.String)
	 */
	@Override
	public String processRequest(String content) {
		String resp = "";
		if (content.equals("1")) { 
			resp =  getHelpMenu();
		} else if (content.equalsIgnoreCase("la")) {
			List <CarBean> cBeans = new CarDAO().getAllCars();
			for (CarBean o : cBeans) {
				resp = resp + getResp(o) + "\n----------------\n";
			}
		} else {
			String[] arr = content.split("\\ ");
			String car, phone;
			CarBean cBean;
			if (arr.length == 1) {
				car = arr[0].toUpperCase();
				if (car.getBytes().length == 5) {car = "辽B"+ car;}
				
				cBean = new CarDAO().find(car);
				if (cBean != null) {return getResp(cBean);}
				{
					resp = "车牌：" + car + " 没找到啊没找到.要不你加一下? 输入1看看添加指令.";
				}
				
			} else if (arr[1].equalsIgnoreCase("delete")) {
				if (new CarDAO().deleteCar(arr[0].toUpperCase())) {
					resp = "车牌：" + arr[0] + "刚刚被删除了";
				} else {
					resp = "车牌：" + arr[0] + "没删成.";
				}
			} else {
				car = arr[0].toUpperCase();
				phone = arr[1].toUpperCase();
				if (!isCarNumber(car)) {
					resp = "你填的车牌号好像不对哦.";
					return resp;
				}
				if(!isPhoneNumber(phone)) {
					resp = "你填的是电话号码吗,别骗人啊";
					return resp;
				}
				
				cBean = new CarDAO().find(car);
				if (cBean != null) {
					CarBean uBean = new CarBean ();
					uBean.setCar(car);
					uBean.setPhone(phone);
					if (new CarDAO().updateCar(uBean)) {
						resp = "车牌：" + car + " 让我给更新了,厉害吧!";
					} else {
						resp = "车牌：" + car + " 没更新成,肿么回事,你是不是搞错了.";
					}
				} else {
					new CarDAO().addCar(car, phone);
					resp = "车牌：" + car + ",联系电话:" + phone + " 已经被加入到数据库中．";
				}
			}
		}
		
		return resp;
	}

	public static String getHelpMenu () {
		 StringBuffer buffer = new StringBuffer();  
	        buffer.append("您好，请发送下列指令：").append("\n\n");  
	        buffer.append("车牌　车主电话").append("\n"); 
	        buffer.append("来添加更新车主电话信息").append("\n"); 
	        buffer.append("或者只输入车牌号").append("\n");
	        buffer.append("大连车牌的话,只输入后5位也行").append("\n");
	        return buffer.toString(); 
	}
	
	private static String getResp (CarBean car) {
		if (car != null) {
			return "车牌:" + car.getCar() + "\n车主电话:" + car.getPhone();
		} else {
			return "没查到,不好意思";
		}
	}
	
	private static boolean isPhoneNumber(String value) {  
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		Matcher m = p.matcher(value);  
		
		return m.matches(); 
	 }  
	private static boolean isCarNumber (String value) {
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}");  
		Matcher m = p.matcher(value);  
		
		return m.matches();
	}
}
