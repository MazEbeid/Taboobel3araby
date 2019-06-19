package mazenebeidcreations.games.taboobel3araby;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import android.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import mazenebeidcreations.games.taboobel3araby.R.color;



public class GameActivity extends Activity {
///Variable declarations
	static String t1Name,t2Name;
	static FrameLayout wordLayout;
	TextView timer,turnLabel,gameScoreLabel,scoreText;
	static int t1score,t2score;
	Button gotITButton, skippedButton,undoButton;
	static Intent goToScoreBoardActivity;
	static Game currentGame;
	static Team team1, team2;
	static TextView w, w1,w2,w3,w4;
	static Random random;
	static Card c;
	static int j;
	static MediaPlayer mp;
	static Scanner s;
 	static int x=9;
	static int count = 0;
	static Queue<String> tempQ;
	static Queue<Card> tempTeam1Q, tempTeam2Q;
	static int backPress=0;
	static boolean gameIsOver;
	static int duration;
	static Intent goToMainActivity;
	static boolean sound;
	static  AssetFileDescriptor afd;
	static CountDownTimer t;
	static int globalCount;
	static SharedPreferences sharedpreferences;
	static SharedPreferences.Editor editor;
	static String userPref;
	///////// On Create view.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
		
		
		wordLayout = (FrameLayout)findViewById(R.id.FrameLayout1);
		
		

		
		tempTeam1Q = new LinkedList<Card>();
		tempTeam2Q = new LinkedList<Card>();
		goToMainActivity = new Intent (this,MainActivity.class);
		mp = new MediaPlayer();
		random = new Random();
		sound=true;
		
		
		
		
		  globalCount = 0;
		  Bundle bundle = getIntent().getExtras();
          if(bundle!=null)
          {
		
          	currentGame = (Game) bundle.getSerializable("game");
          	sound = bundle.getBoolean("sound");
          	duration = bundle.getInt("duration");
          	team1 = currentGame.team1;
          	team2 = currentGame.team2;
        	createTeamQueues(MainActivity.end);
       	 
          }
	
		
		//Variables Initilialzation
		
		
		goToScoreBoardActivity = new Intent (this, ScoreBoardActivity.class);
		timer = (TextView)findViewById(R.id.counterLabel);
		
		turnLabel = (TextView)findViewById(R.id.turnLabel);
		turnLabel.setTypeface(MainActivity.typeFace1);
		
		scoreText = (TextView)findViewById(R.id.scoreTextView);
		scoreText.setTypeface(MainActivity.typeFace1);
		
		gameScoreLabel = (TextView)findViewById(R.id.gameScoreLabel);
		gameScoreLabel.setTypeface(MainActivity.typeFace1);
		
		w = (TextView)findViewById(R.id.wordLabel);
		w.setTypeface(MainActivity.typeFace1);
		w1 = (TextView)findViewById(R.id.word1Label);
		w2 = (TextView)findViewById(R.id.word2Label);
		w3 = (TextView)findViewById(R.id.word3Label);
		w4 = (TextView)findViewById(R.id.word4Label);
	  	//Read words from Queue
  	
	 
		
		//Get Intent data
	          
		
	
 
		
	 
			
// Turns	
		//initial team name and score setup
		if(currentGame.turn%2==0)
		{
			
			turnLabel.setText("دور "+team1.teamName);
			gameScoreLabel.setText(" "+t1score);
		
			/// setup 1st card for team 1
			if(team1.teamCardsQ.peek()!=null)
			{
				 c= team1.teamCardsQ.poll();
				displayCard(c);
				globalCount++;
			}
			else
			{
				shuffleCards();
				createTeamQueues(MainActivity.end);
				toasting("Cards Shuffled");
				 c= team1.teamCardsQ.poll();
					displayCard(c);
			}
		}
		else
			/// setup 1st card for team 2
			if(currentGame.turn%2!=0)
			{
				
					turnLabel.setText("دور "+team2.teamName);
					gameScoreLabel.setText(" "+t2score);
					wordLayout.setBackgroundResource(R.drawable.red_word_layout);
					if(team2.teamCardsQ.peek()!=null)
					{	
					 c= team2.teamCardsQ.poll();
					displayCard(c);
				   
					}
					else
					{
						shuffleCards();
						createTeamQueues(MainActivity.end);
						c= team1.teamCardsQ.poll();
						displayCard(c);
					}
			}
	
///Game counter		
t = 	new CountDownTimer(duration, 1000) {

			
		     public void onTick(long millisUntilFinished) {
		    	 int seconds = (int) (millisUntilFinished/ 1000) % 60 ;
		    	 int minutes = (int) ((millisUntilFinished / (1000*60)) % 60);
		    	 if(seconds>=10)
		    	 {
		    		 timer.setText("" +minutes+":"+seconds);
		    		 
		    	 }
		    	 else
		    	 {
		    		 timer.setText("" +minutes+":0"+seconds);
		    	 }
		     }

		     public void onFinish() {
		       
	
		     Game.turn++;
		   	t.cancel();
		 		goToScoreBoardActivity.putExtra("t1score", t1score);
		 		goToScoreBoardActivity.putExtra("t2score", t2score);
		 		goToScoreBoardActivity.putExtra("t1Name",team1.teamName);
		 		goToScoreBoardActivity.putExtra("t2Name",team2.teamName);
		 		 
		 			if(gameOver()==false)
		 			{
		 				ScoreBoardActivity.round++;
		 			}
		 			
		 			startActivity(goToScoreBoardActivity);
		 			finish();
		 		
		 		 }
		     
		       }.start();
	/////Counter code ends	  
		  
/////////////////////////////////////////GOTIT BUTTON///////////////////////////////////////////////////////////////
		  gotITButton = (Button)findViewById(R.id.gotItButton);
		  gotITButton.setTypeface(MainActivity.typeFace1);
		  gotITButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
//increment or decrement score depeding on turn			
				if(sound==true)
				{		
				 playSound("smw_coin.wav");

				}
	//Team 1 turn		

			if(!gameOver())	
			{
				if(currentGame.turn%2==0)
			
				{
						t1score++;
						gameScoreLabel.setText(" "+t1score);
						if(gameOver())
					{
						endGame();
					}
					else
					{
						if(team1.teamCardsQ.peek()!=null)
						{
							c= team1.teamCardsQ.poll();
							displayCard(c);
							globalCount++;
						}	
					
					
					else{
					
						
						shuffleCards();
						createTeamQueues(MainActivity.end);
						
						
						
					}
					}
					
				}
	//Team 2 Turn
				else
					if(currentGame.turn%2!=0)
					{
					
						
						
						t2score++;
						gameScoreLabel.setText(" "+t2score);
						if(gameOver())
						{
							endGame();
							
						}
						else
						{
							if(team2.teamCardsQ.peek()!=null)
							{
								
								 c= team2.teamCardsQ.poll();
								displayCard(c);
								
							}	
									
						
						else{
						
							
							shuffleCards();
							createTeamQueues(MainActivity.end);
							
							
							
						}
						}
						
			}
			
			}///view on vclick
			else
			{
				endGame();
			}
			}
		});  
		  
///////////////////////////////////////////SKIP BUTTON//////////////////////////////////////////////////////////		  
		  skippedButton = (Button)findViewById(R.id.skipButton);
		  skippedButton.setTypeface(MainActivity.typeFace1);
		  skippedButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(sound==true)
				{
					playSound("fart.wav");
			    }
		//Team 1		
				
				if(!gameOver())
				{
				if(currentGame.turn%2==0)
				{
						
						t1score--;
						gameScoreLabel.setText(" "+t1score);
						if(gameOver())
						{
							endGame();
							
						}
					if(team1.teamCardsQ.peek()!=null)
					{
							
							 c= team1.teamCardsQ.poll();
							displayCard(c);
							globalCount++;
					}	
					
					
					else{
					
						shuffleCards();
						createTeamQueues(MainActivity.end);
						}
								
					
				
				}	
						
				
			
		  
					
				
				else //Team 2
					if(currentGame.turn%2!=0)
					{
						
						
							t2score--;
							gameScoreLabel.setText(" "+t2score);
					
							if(gameOver())
							{
								endGame();
								
							}
							
					if(team2.teamCardsQ.peek()!=null)
					{
							
							 c= team2.teamCardsQ.poll();
							displayCard(c);
							
					}	
					
					else{
						shuffleCards();
						createTeamQueues(MainActivity.end);
						
					}
					
					
						
					}	
			}	
				else
				{
					endGame();
				}
			}
			
		});  
		
///////////////////////////////////////UNDO BUTTON///////////////////////////////////////////////////////		  
		  undoButton = (Button)findViewById(R.id.undoButton);
		  undoButton.setTypeface(MainActivity.typeFace1);
		  undoButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					if(sound==true)
					{
						playSound("woop.wav");
				    }
					
					toasting("You have been NAUGHTY!");
					
				if(!gameOver())	
				{
					if(currentGame.turn%2==0)
					{
						t1score=t1score-2;
						gameScoreLabel.setText(" "+t1score);
						if(gameOver())
						{
							endGame();
						}
						
					}
					else if(currentGame.turn%2!=0)
					{
						t2score=t2score-2;
						gameScoreLabel.setText(" "+t2score);
						if(gameOver())
						{
							endGame();
						}
					}
				}
				else
				{
					endGame();
				}
				}
				
				
		  }); 
		  
	}/////////////////////////END OF ONCREATE METHOD/////////////////////////////////////////////////////////////////

	
///////////////////////////////////////////////HELPING METHODS////////////////////////////////////////////////////////


	private static int returnRandomInteger(int aStart, int aEnd, Random aRandom){
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    return randomNumber;
	  }

	
	
	public static void displayCard (Card c)
	{
		w.setText(c.word);
		w1.setText(c.word1);
		w2.setText(c.word2);
		w3.setText(c.word3);
		w4.setText(c.word4);
	}
	
	public static void displayTeamQueues()
	{
		 while(tempTeam1Q.peek()!=null)
		 {
			 Card c = tempTeam1Q.poll();
			 count++;
			 Log.d("Team 1 Queue", c.word);
		
		 }
 Log.d("Team 1 Queue", "Total number of cards: "+count);
 int count2=0;
	 while(tempTeam2Q.peek()!=null)
	 {
		 Card c = tempTeam2Q.poll();
		count2++;
		 Log.d("Team 2 Queue", c.word);
		 
	 }
	 Log.d("Team 2 Queue", "Total number of words: "+count2);
	 
	 
	  	Log.d("Game Cards", "There are currently"+""+count+"Cards in the queue");

	}


public static void createTeamQueues(int end)
{
	int index =0;
	int START = 0;
	int END = end;
	
	
	j = returnRandomInteger(START, END, random);
	
	while(index<=MainActivity.numberOfTeamCards-1)
 	 {
 		 if(MainActivity.gameCards[j].played==false)
 		 {
 			 team1.teamCardsQ.offer(MainActivity.gameCards[j]);
 			 tempTeam1Q.offer(MainActivity.gameCards[j]);
 			 MainActivity.gameCards[j].played=true;
 			 index++;
 		 }
 		 else
 		 {
 			 j=returnRandomInteger(START, END, random);
 			
 		 }
 		 
 	 
 	 }
	
	index=0;
	while(index<=MainActivity.numberOfTeamCards-1)
 	 {
 		 if(MainActivity.gameCards[j].played==false)
 		 {
 			 team2.teamCardsQ.offer(MainActivity.gameCards[j]);
 			 tempTeam2Q.offer(MainActivity.gameCards[j]);
 			 MainActivity.gameCards[j].played=true;
 			 index++;
 		 }
 		 else
 		 {
 			 j=returnRandomInteger(START, END, random);
 			
 		 }
 		 
 	 
 	 }
	

displayTeamQueues();

}

public static void shuffleCards()
{
	toasting("Shuffling "+globalCount +"cards played");
	//toasting("You ran out of playing cards, Shuffling Cards");
	
	Log.d("Game Cards Shuffled",""+count);
	int count=0;
	for(int i=0;i<MainActivity.gameCards.length;i++)
	{
		
		if(MainActivity.gameCards[i].played==true)
		{
			MainActivity.gameCards[i].played=false;
			count++;
		}
	}
	for(int i=0; i<MainActivity.gameCards.length-1;i++)
	{
		Log.d("Game Cards after shuffling ",""+MainActivity.gameCards[i].played);
	}
	
  }

public static boolean gameOver()
{
	
	if (t1score>14)
	{
	ScoreBoardActivity.winner = "Team 1";
		return true;
	}
	else if(t2score>14)
	{
		ScoreBoardActivity.winner = "Team 2";
		return true;
	}
	
	else if(t1score<-9)
	{
		ScoreBoardActivity.winner = "Team 2";
		return true;
	}
	
	else if(t2score<-9)
	{
		ScoreBoardActivity.winner = "Team 1";
		return true;
	}
	
	
	else
		return false;
}


public static void resetGame()
{
	t1score = 0;
	t2score = 0;
	ScoreBoardActivity.round=0;
	currentGame.turn = 0;
	shuffleCards();
}

	
public  void endGame()
{
		shuffleCards();
		goToScoreBoardActivity.putExtra("t1score", t1score);
		goToScoreBoardActivity.putExtra("t2score", t2score);
		goToScoreBoardActivity.putExtra("t1Name",team1.teamName);
		goToScoreBoardActivity.putExtra("t2Name",team2.teamName);
		t.cancel();
		startActivity(goToScoreBoardActivity);
		finishAffinity();
	
}


public void playSound(String fileName)
{
		
	 if(mp.isPlaying())
        {  
            mp.stop();
        } 

        try {
            mp.reset();
            afd = getAssets().openFd(fileName);
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


}
public static void toasting(String text)
{
	MainActivity.toast.setText(text);
	MainActivity.toast.show();
}

public static boolean forfeitGame(int score)
{
	boolean f = false;
	
	if(score<-14)
	{
		f=true;
	}
	return f;
	
}




//////////////////////////////OVERRIDE METHODS///////////////////////////////////////////////////////

@Override
public void onBackPressed() {
	
	if(backPress==0)
	{
		toasting("Press back one more time to exit game");
		MainActivity.toast.show();
		backPress++;
	}	
	else if(backPress>0)
	{
		backPress=0;
		t.cancel();
		startActivity(goToMainActivity);

		finish();
	}
}
}//////////////////////////////////END OF CLASS//////////////////////////////////////////////////////////
// for(int i=0; i<MainActivity.gameCards.length-1;i++)
//	 {
//		 Card c = MainActivity.gameCards[i];
//		 Log.d("Game Cards", c.word+"\n"
//								 +c.word1+"\n"+
//								c.word2+"\n"+
//								 c.word3+"\n"+
//								c.word4+"\n");
	//					 Log.d("Game Cards", "Next Card");
		 
						 
						 
//	 }
	
// Log.d("Random Count", ""+end);

	// Get any intent data from other views


