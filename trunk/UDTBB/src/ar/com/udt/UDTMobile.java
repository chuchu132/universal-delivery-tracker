package ar.com.udt;

import java.util.Timer;
import java.util.TimerTask;

import net.rim.device.api.system.Application;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class UDTMobile extends MainScreen{
	
	private UiApplication _app;
	
	public UDTMobile( UiApplication _app) {
		super(MainScreen.NO_VERTICAL_SCROLL | MainScreen.USE_ALL_WIDTH);
		this._app = _app;
		setLayout();
		
	}
	
	protected void onVisibilityChange(boolean visible) {
		super.onVisibilityChange(visible);
		if(visible){
			Timer t = new Timer();
			t.schedule(new TimerTask() {
				public void run() {
					synchronized (Application.getEventLock()) {
						_app.popScreen(UDTMobile.this);
						_app.pushScreen(new LoginScreen());	
					}
				}
			}, 1000);
		}
	}
	private void setLayout() {
		VerticalFieldManager vfm = new VerticalFieldManager(VerticalFieldManager.USE_ALL_HEIGHT | VerticalFieldManager.USE_ALL_WIDTH);
		vfm.setBackground(BackgroundFactory.createBitmapBackground(Bitmap.getBitmapResource("img/udtsplash.png"),0,0,Background.REPEAT_SCALE_TO_FIT));
		add(vfm);
	}
	
	

	protected boolean onSavePrompt() {
		return true;
	}
}