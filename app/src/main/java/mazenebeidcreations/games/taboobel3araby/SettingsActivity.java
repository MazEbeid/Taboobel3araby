package mazenebeidcreations.games.taboobel3araby;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SettingsActivity extends Activity {
	
////// VARIABLES DECLARATIONS	
	TextView roundDurationLabel,playSoundsLabel,numberOfCardsLabel,numberOfCards,purchaseOptions;
	EditText durationField;
	static Button saveButton;
	static CompoundButton playSoundsButton;
	static RadioButton radio1,radio2,radio3;
	static Intent gotToMainActivity;
	static boolean sound;
	static int duration;
	static Intent goToMainActivity;

	View myView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);




		goToMainActivity = new Intent(this,MainActivity.class);
		
	///////////////////////VARIRABLES INIT/////////////////////////	
		roundDurationLabel = (TextView)findViewById(R.id.roundDurationLabel);
		numberOfCardsLabel = (TextView)findViewById(R.id.numberOfCardsLabel);
		numberOfCards = (TextView)findViewById(R.id.numberOfCards);
		numberOfCards.setTypeface(MainActivity.typeFace);
		playSoundsLabel = (TextView)findViewById(R.id.soundLabel);
		saveButton = (Button)findViewById(R.id.saveButton);
		numberOfCards.setText(""+MainActivity.numberOfCards);
		duration =120000;
		sound = true;
		
		gotToMainActivity = new Intent(this, MainActivity.class);
		radio1 = (RadioButton)findViewById(R.id.radioButton1);
		radio1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			radio1.setChecked(true);
			radio2.setChecked(false);
			radio3.setChecked(false);
			duration = 60000;		
			toasting("Round duration is "+1+" min");	
			}
		});
		
		radio2 = (RadioButton)findViewById(R.id.radioButton2);
		radio2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			radio1.setChecked(false);
			radio2.setChecked(true);
			radio3.setChecked(false);
			duration = 120000;		
			toasting("Round duration is "+2+" mins");	
				
			}
		});
		
		radio3 = (RadioButton)findViewById(R.id.radioButton3);
		radio3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			radio1.setChecked(false);
			radio2.setChecked(false);
			radio3.setChecked(true);
			duration = 180000;		
			toasting("Round duration is "+3+" mins");
				
				
			}
		});
		
		
		playSoundsButton = (ToggleButton)findViewById(R.id.playSoundsButton);
		playSoundsButton.setTypeface(MainActivity.typeFace);
		playSoundsButton.setChecked(true);
		playSoundsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String s = "";
			if(sound==true)
			{
				sound=false;
				s="OFF";
			}
			
			else if(sound==false)
			{
				sound=true;
				s="ON";
			}
				
			toasting("Sound is "+s);
				
				
			}
		});
		
saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				toasting("Settings Saved");
				gotToMainActivity.putExtra("duration", duration);
				gotToMainActivity.putExtra("sound",sound);
				
				startActivity(gotToMainActivity);
		
				
				
			}
		});
		
	

	
	}

public static void toasting(String text)
{
	MainActivity.toast.setText(text);
	MainActivity.toast.show();
}




	@Override
	public void onBackPressed() {
		startActivity(goToMainActivity);
		finish();
	}





}
