package com.ipcamera;


import android.app.Activity;
import android.content.Context;
import java.net.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.ipcamera.MjpegView;
import com.ipcamera.MjpegInputStream;

public class ArduinoActivity extends Activity {
    /** Called when the activity is first created. */
	private MjpegView mv;
    Button on;
	Button off;
	
	
	String serverHostname1;
    DatagramSocket d1;
    InetAddress ip,retiip;
    DatagramPacket send,rec;
    String modifiedSentence;
    private Boolean isOnline()  {
    		    ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    		    NetworkInfo ni = cm.getActiveNetworkInfo();
    		    if(ni != null && ni.isConnected())
    		        return true;
    		 
    		    return false;
    		}
    
    public void led(String s) throws Exception
    {
    	
    	byte[] b=(s.getBytes());
    	if(isOnline())
    	{
    

        serverHostname1 = new String ("192.168.0.177");
  
        ip = InetAddress.getByName(serverHostname1); 

       
		d1 = new DatagramSocket();//}

        try{
        	send =  new DatagramPacket(b,b.length, ip, 8888);	
        }catch(Exception e){
	
        }
        

        d1.send(send); 

        d1.setSoTimeout(10000);
        d1.receive(rec); 
        modifiedSentence =   new String(rec.getData());
        InetAddress returnIPAddress = rec.getAddress();

        Toast.makeText(getApplicationContext(),"Reply from Server:"+returnIPAddress,Toast.LENGTH_LONG).show();
        
        d1.close(); 
    	}
    	else
    	{
    		Toast.makeText(getApplicationContext(),"No network",Toast.LENGTH_LONG).show();
    	}
    }
    	   
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        on=(Button)findViewById(R.id.on);
        off=(Button)findViewById(R.id.off);
        
        
        on.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {

					
					led("001");
					Toast.makeText(getApplicationContext(),"RIGHT",Toast.LENGTH_LONG).show();
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error::"+e);
				}
				
			}
		});
        
        
        off.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {

					led("002");
					Toast.makeText(getApplicationContext(), "LEFT",Toast.LENGTH_LONG).show();
					
				} catch (Exception e) {
					 //TODO Auto-generated catch block
					System.out.println("Error::"+e);
				}
				
			}
		});
        

        mv = (MjpegView)findViewById(R.id.mv1);

        
    }
        
    protected void onResume() {
        super.onResume();
        String URL = "http://192.168.0.27:80/video.cgi";
        mv.setSource(MjpegInputStream.read(URL));
        mv.init(this);
        mv.showFps(true);
       }
    public void onPause() {
		super.onPause();
		mv.stopPlayback();
	}   
}