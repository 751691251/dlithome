package com.dlithome.message.request;

public class VoiceMessage extends BaseMessage {

	public VoiceMessage() {
		// TODO Auto-generated constructor stub
	}

    private String MediaId;  

    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
}
