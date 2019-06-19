package mazenebeidcreations.games.taboobel3araby;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HowToPlayActivity extends Activity {

	////Variables Declarations
	static TextView introductionLabel, introductionP1,introductionP2,introductionP3, howToPlayLabel, howToPlayP1, howToPlayP2,howToPlayP3, pointersLabel, pointersP1,pointersP2;
	Intent goToMainActivity;
@Override
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.howtoplay_activity);
///////////////////////////Variables Init/////////////////////////////////////////////////		
		introductionLabel = (TextView)findViewById(R.id.introductionLabel);
		introductionLabel.setTypeface(MainActivity.typeFace);
		introductionP1   = (TextView)findViewById(R.id.introductionP1);
		introductionP1.setTypeface(MainActivity.typeFace1);
		introductionP2   = (TextView)findViewById(R.id.introductionP2);
		introductionP2.setTypeface(MainActivity.typeFace1);
		introductionP3   = (TextView)findViewById(R.id.introductionP3);
		introductionP3.setTypeface(MainActivity.typeFace1);
		
		
		howToPlayLabel = (TextView)findViewById(R.id.howToPlayLabel);
		howToPlayLabel.setTypeface(MainActivity.typeFace);
		howToPlayP1     = (TextView)findViewById(R.id.howToPlayP1);
		howToPlayP1.setTypeface(MainActivity.typeFace1);
		howToPlayP2     = (TextView)findViewById(R.id.howToPlayP2);
		howToPlayP2.setTypeface(MainActivity.typeFace1);
		howToPlayP3     = (TextView)findViewById(R.id.howToPlayP3);
		howToPlayP3.setTypeface(MainActivity.typeFace1);
		pointersLabel  = (TextView)findViewById(R.id.pointersLabel);
		pointersLabel.setTypeface(MainActivity.typeFace);
		pointersP1      = (TextView)findViewById(R.id.pointersP1);
		pointersP1.setTypeface(MainActivity.typeFace1);
		pointersP2      = (TextView)findViewById(R.id.pointersP2);
		pointersP2.setTypeface(MainActivity.typeFace1);

	goToMainActivity = new Intent(this,MainActivity.class);
		
		
		
		
		
	}//////////////////////////////////END OF ONCREATE//////////////////////////////////////////////////////

	@Override
	public void onBackPressed() {
		finish();
		startActivity(goToMainActivity);
	}
}/////////////////////////////////////////END OF CLASS/////////////////////////////////////////////////////
