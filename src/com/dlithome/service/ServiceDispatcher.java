package com.dlithome.service;

import java.util.Date;  
import java.util.Map;  

import javax.servlet.http.HttpServletRequest;  

import com.dlithome.message.response.TextMessage;  
import com.dlithome.utils.MessageUtil;  
  
/** 
 *  
 * @author liufeng 
 * @date 2013-05-20 
 */  
public class ServiceDispatcher {  
    /** 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
     
            String respContent = getMainMenu();  
  
          
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
         
            String fromUserName = requestMap.get("FromUserName");  
           
            String toUserName = requestMap.get("ToUserName");  
            
            String msgType = requestMap.get("MsgType");  
  
           
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
  
            System.out.println(requestMap.get("Content"));
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
            	
            	String text = requestMap.get("Content").toLowerCase();
            	System.out.println("length is " + text.getBytes().length);
            	String[] arr = text.split("\\ ");
            	
            	
            	
            	if (text.equals("1") 
            				|| (text.getBytes().length == 7)
            				|| (text.getBytes().length == 5)
            				|| (text.equalsIgnoreCase("la"))
            				|| ((arr.length == 2) && (arr[1].length() == 11) && (arr[0].getBytes().length == 7))
            				|| ((arr.length == 2) && (arr[1].equalsIgnoreCase("delete")))) {
            		respContent = new CarService().processRequest(text);
            	} else if (text.equals("2")) {
            		respContent = new BusService().processRequest(text);
            	}
                
            }  
            
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "图片信息";  
            }  
            
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "位置信息";  
            }  
          
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "链接信息";  
            }  
            // ��Ƶ��Ϣ  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "语音";  
            }  
           
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                
                String eventType = requestMap.get("Event");  
               
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "欢迎你关注,目前只支持添加,查询小区内的挪车电话，小区班车时刻表查询";  
                }  
                
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                	respContent = "你就这么走了啊,用医范伟的话说,不送!!!!!";  
                }  
                
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                  
                }  
            }  
  
            textMessage.setContent(respContent);  
            respMessage = MessageUtil.textMessageToXml(textMessage);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  
    
    public static String getMainMenu() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("您好，请回复数字选择服务：").append("\n\n");  
        //buffer.append("1  车主电话查询").append("\n"); 
        buffer.append("2  小区班车时刻表").append("\n");  
        /*
        buffer.append("3  周边搜索").append("\n");  
        buffer.append("4  歌曲点播").append("\n");  
        buffer.append("5  经典游戏").append("\n");  
        buffer.append("6  美女电台").append("\n");  
        buffer.append("7  人脸识别").append("\n");  
        buffer.append("8  聊天唠嗑").append("\n\n");  */
        //buffer.append("回复“?”显示此帮助菜单");
        
        return buffer.toString();  
    }  
}  