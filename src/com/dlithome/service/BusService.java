/**
 * 
 */
package com.dlithome.service;

/**
 * @author Jerry
 *
 */
public class BusService implements ServiceInterface {

	/**
	 * 
	 */
	public BusService() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.dlithome.service.ServiceInterface#processRequest(java.lang.String)
	 */
	@Override
	public String processRequest(String content) {
		StringBuffer buffer = new StringBuffer(); 
		if (content.equals("2")) {
			buffer.append("--百合发车---小区发车--").append("\n\n");
			buffer.append("---6:00--------6:15-----").append("\n");
			buffer.append("---6:30--------6:45-----").append("\n");
			buffer.append("---7:00--------7:15-----").append("\n");
			buffer.append("---7:30--------7:45-----").append("\n");
			buffer.append("---8:00--------8:15-----").append("\n");
			buffer.append("---9:00--------9:15-----").append("\n");
			buffer.append("---10:00-------10:15----").append("\n");
			buffer.append("---11:00-------11:15----").append("\n");
			buffer.append("---12:00-------12:15----").append("\n");
			buffer.append("---13:00-------13:15----").append("\n");
			buffer.append("---14:00-------14:15----").append("\n");
			buffer.append("---15:00-------15:15----").append("\n");
			buffer.append("---15:30-------16:00----").append("\n");
			buffer.append("---16:15-------16:30----").append("\n");
			buffer.append("---16:45-------17:00----").append("\n");
			buffer.append("---17:15-------17:30----").append("\n");
			buffer.append("---17:45-------18:00----").append("\n");
			buffer.append("---18:15-------18:30----").append("\n");
			buffer.append("---18:45-------19:00----").append("\n");
			buffer.append("---19:15-------19:30----").append("\n");
			buffer.append("---19:45-------20:00----").append("\n\n");
			
			buffer.append("--排队上车--文明乘车--").append("\n");
		}
		return buffer.toString();
	}

}
