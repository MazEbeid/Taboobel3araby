package mazenebeidcreations.games.taboobel3araby;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;




public class ScoreBoardActivity extends Activity {

	Button startNextRoundButton, endGameButton;
	TextView t1ScoreLabel, t2ScoreLabel,team1Label,team2Label, roundNumber, scoreBoardLabel;
	static int round = 0;
	static String winner ="";
	static Intent goToMainActivity;
	
	
	
	
	static Intent goToGameIntent,goToSetupIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scoreboard_activity);

		

		scoreBoardLabel = (TextView)findViewById(R.id.scoreBoardLabel);
		t1ScoreLabel = (TextView)findViewById(R.id.team1ScoreLabel);
		t2ScoreLabel = (TextView)findViewById(R.id.team2ScoreLabel);
		team1Label = (TextView)findViewById(R.id.team1SLabel);
		team2Label = (TextView)findViewById(R.id.team2SLabel);
		roundNumber = (TextView)findViewById(R.id.roundNumber);
		endGameButton = (Button)findViewById(R.id.endGameButton);
		
		startNextRoundButton = (Button)findViewById(R.id.startNextRoundButton);
		
		goToMainActivity = new Intent(this,MainActivity.class);
		goToGameIntent = new Intent(this,GameActivity.class);
		 Bundle bundle = getIntent().getExtras();
		 if (bundle !=null)
		 {
			 t1ScoreLabel.setText(bundle.get("t1score").toString());
			 t2ScoreLabel.setText(bundle.get("t2score").toString());
		
		 }
		
		
		
		if(GameActivity.gameOver()==false)
		{
		
			   
				startNextRoundButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
					
						
						startActivity(goToGameIntent);
						finish();
						
					}
				});
				
				 roundNumber.setText("Round : "+round);
		 
		 }

		else
		{
			
			startNextRoundButton.setText("هنلعب تاني ");////////////////////////
			GameActivity.resetGame();
			scoreBoardLabel.setText("الجيم خلص ");
			roundNumber.setText(winner+" كسب");
			
		   startNextRoundButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
						
							startActivity(goToGameIntent);
							finish();
							
						
					
				}
			});
			
		}
		 
		
		
		
		endGameButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				startActivity(goToMainActivity);
				finish();
				
				
			}
		});
			}
		
		
		
		
		




	
}
