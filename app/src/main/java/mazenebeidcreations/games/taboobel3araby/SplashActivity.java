package mazenebeidcreations.games.taboobel3araby;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity{

static Intent goToMainActivity;
static CountDownTimer t;
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_activity);
		
		goToMainActivity = new Intent(this, MainActivity.class);
		
		t = 	new CountDownTimer(2000, 1000) {

			
		     public void onTick(long millisUntilFinished) {
		    	 int seconds = (int) (millisUntilFinished/ 1000) % 60 ;
		    	 int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
		    	  
		     }

		     public void onFinish() {
		       
	
		   	t.cancel();
		 		
		 			
		 			
		 			startActivity(goToMainActivity);
		 			finish();
		 		
		 		 }
		     
		       }.start();
		
	
}


}
