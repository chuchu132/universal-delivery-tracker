package ar.com.udt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class LoginActivity extends Activity {

	private Button loginButton;
	private Button registerButton;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
		setContentView(R.layout.login);
		loginButton = (Button) findViewById(R.id.login_button);
		registerButton = (Button) findViewById(R.id.register_button);
		
		loginButton.setOnClickListener(loginClickListener);
		registerButton.setOnClickListener(registerClickListener);
	}
	
	private final OnClickListener loginClickListener =  new OnClickListener() {
		public void onClick(View v) {
			Intent i =  new Intent(LoginActivity.this, HomeActivity.class);
			startActivity(i);
		}
	};
	
	private final OnClickListener registerClickListener =  new OnClickListener() {
		public void onClick(View v) {
			Intent i =  new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(i);
		}
	};
}
