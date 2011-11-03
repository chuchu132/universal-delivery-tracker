package ar.com.udt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegisterActivity extends Activity {

	Button register;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.register);
	    
	    register = (Button) findViewById(R.id.register_button);
	    register.setOnClickListener(registerOnClickListener);
	}

	private OnClickListener registerOnClickListener = new OnClickListener() {
		public void onClick(View v) {
			RegisterActivity.this.finish();
		}
	};
}
