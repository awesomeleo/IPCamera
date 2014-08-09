/**
 *  Camera Surveillance Controller System
 *  @version 1.1
 *  http://www.instructables.com/member/faziefazie/
 *  http://www.instructables.com/id/Camera-Surveillance-Controller-System/
 *  @author Faziefazie (Fazie Romadhona)
 *  Indonesia. (2014)
 */

package com.ipcamera;


import com.ipcamera.MainActivity;


import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	Button btnStream, btnActAbout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main_activity);
	    
	    TextView textv = (TextView) findViewById(R.id.textView1);
	    textv.setShadowLayer(1, 3, 3, Color.GRAY);
	    
	    btnStream = (Button) findViewById(R.id.button_stream);
	    btnStream.setOnClickListener(this);
	       
	    btnActAbout = (Button) findViewById(R.id.button_about);
	    btnActAbout.setOnClickListener(this);
	    
	}

	public void onClick(View v) {
		switch (v.getId()) {    	
	    case R.id.button_stream:
	    	Intent intent_stream = new Intent(this, ArduinoActivity.class);
	    	startActivity(intent_stream);
	    	break;  

	    case R.id.button_about:
	    	Intent intent_about = new Intent(this, ActivityAbout.class);
	    	startActivity(intent_about);
	    	break;	   	
    	
	    default:
	    	break;
	    }
	}
  
}
