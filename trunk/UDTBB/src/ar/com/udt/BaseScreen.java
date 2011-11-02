package ar.com.udt;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Background;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class BaseScreen extends MainScreen {
	
	private LabelField _screenTitle;
	private VerticalFieldManager _content;
	VerticalFieldManager _titleBox;
	public BaseScreen() {
		super(USE_ALL_HEIGHT | USE_ALL_WIDTH | NO_VERTICAL_SCROLL);
		setUp();
	}
	
	private void setUp(){
		
		_titleBox = new VerticalFieldManager(USE_ALL_WIDTH);
		_titleBox.setPadding(10, 0, 10, 0);
		_titleBox.setBackground(BackgroundFactory.createBitmapBackground(Bitmap.getBitmapResource("img/backgroud_title.png"), 0,0,Background.REPEAT_SCALE_TO_FIT));
		_screenTitle =  new LabelField("LOGIN"){
			protected synchronized void paint(Graphics aGraphics) {
				aGraphics.setColor(0xffffff);
				super.paint(aGraphics);
			}
			
			public int getPreferredWidth() {
				return getFont().getAdvance(getText());
			}
			
		};
		_screenTitle.setEditable(false);
		_titleBox.add(_screenTitle);
		super.add(_titleBox);
		_content =  new VerticalFieldManager(USE_ALL_HEIGHT | USE_ALL_WIDTH);
		_content.setBackground(BackgroundFactory.createBitmapBackground(Bitmap.getBitmapResource("img/bg_common.png"),0,0,Background.REPEAT_SCALE_TO_FIT));
		super.add(_content);
	}
	
	public void add(Field field) {
		_content.add(field);
	}
	
	public void setTitle(String title){
			_screenTitle.setText(title);
			int w = _screenTitle.getPreferredWidth();
			int ww  = Display.getWidth();
			int padding = (int)((ww -w)/2);
			_titleBox.setPadding(10, 0, 10, padding);
			invalidate();			
	}

}
