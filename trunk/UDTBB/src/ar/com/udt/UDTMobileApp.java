package ar.com.udt;

import net.rim.device.api.ui.UiApplication;

public class UDTMobileApp extends UiApplication{
	
	public UDTMobileApp() {
		pushScreen(new UDTMobile(this));
	}
	
	  public static void main(String[] args){
	    	UDTMobileApp instance = new UDTMobileApp();       
	        instance.enterEventDispatcher();
	    } 

}
