package ar.com.udt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class HomeActivity extends Activity {

	Button dondeEstoyButton;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
	    setContentView(R.layout.home);
	    dondeEstoyButton = (Button) findViewById(R.id.dondeestoy);
	    dondeEstoyButton.setOnClickListener(clicListener);
	}
	
	private final OnClickListener clicListener = new OnClickListener() {
		public void onClick(View v) {
			if(v== dondeEstoyButton){
				Intent i = new Intent(HomeActivity.this, DondeEstoyActivity.class);
				startActivity(i);
			}
		}
	};

}
