package mazenebeidcreations.games.taboobel3araby;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;



import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends Activity{

Button newGameButton,exitGameButton;
static Card [] gameCards;
	static cardDB[]gameCards2;
static Intent goToSetUpActivity;
static String t1Name,t2Name;
static Game g;
static Typeface typeFace,typeFace1;
static Intent goToGameActivity, goToSettingsActivity, goToRateActivity, goToHowToPlayActivity, goToAddCardActivity;
static TextView welcomeLabel,welcomeLabel1,welcomeLabel2;
static Toast toast;
static Button InfoButton, AddCardButton, SettingsButton, RateButton;
static  CharSequence text;
static int duration;
static Context context;
static int backPress;
static int roundDuration;
static boolean sound;
static Bundle bundle;	
static Toolbar toolbar;
static Menu main;
static 	Scanner s;
static int wordCount;
static int numberOfCards;
static  int numberOfTeamCards;
static int end;
static Queue<String> tempQ;


static SharedPreferences p;
static FileOutputStream fos;
static String pack1, pack2,rated,normal,buildCode;
String wrd,wrd1,wrd2,wrd3,wrd4;
static boolean newGame;
	static String Target_Translate = "Translate";
    static String target_op = Target_Translate; //dummy default
	DrawerLayout sideDrawer;
    ListView sideDrawerList;
    ImageButton openOptionsDrawerButton;




@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



   sideDrawer = (DrawerLayout)findViewById(R.id.side_drawer);
   sideDrawerList = (ListView)findViewById(R.id.side_drawer_list);
   openOptionsDrawerButton = (ImageButton)findViewById(R.id.options_button);

    DrawerListItem sideDrawerListItems[] = new DrawerListItem[]
            {

                    new DrawerListItem(R.drawable.settingsicon,"Settings"),
                    new DrawerListItem(R.drawable.infoicon,"How to play"),
                    new DrawerListItem(R.drawable.addcardicon,"Add card"),
                    new DrawerListItem(R.drawable.rateicon,"Like us!!"),



            };

    DrawerListItemAdapter sideDrawerListAdapter = new DrawerListItemAdapter(this, R.layout.side_drawer_list_item, sideDrawerListItems);

    sideDrawer = (DrawerLayout)findViewById(R.id.side_drawer);
    sideDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    sideDrawerList = (ListView)findViewById(R.id.side_drawer_list);
    sideDrawerList.setAdapter(sideDrawerListAdapter);

    sideDrawer.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if(sideDrawer.isDrawerOpen(GravityCompat.END))
            {


            }

            return false;
        }
    });

    sideDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if(position==0)
            {
                startActivity(goToSettingsActivity);
            }
            if(position==1)
            {
				startActivity(goToHowToPlayActivity);
            }
            if(position==2)
            {
				startActivity(goToAddCardActivity);
            }
            if(position==3)
            {
				startActivity(new Intent(Intent.ACTION_VIEW,
						Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
            }
            if(position==4)
            {

            }
            if(position==5)
            {


            }
            if(position==6)
            {

            }
            if(position==7)
            {

            }

        }
    });


    openOptionsDrawerButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            sideDrawer.openDrawer(GravityCompat.END);
        }
    });
	
	newGame = false;
	roundDuration = 120000;
	sound = true;
	bundle = getIntent().getExtras();	
	if(bundle!=null)
	{
		roundDuration = bundle.getInt("duration");
		sound = bundle.getBoolean("sound");
		Log.d("Get Sound", ""+sound);
	}
	
	buildGameCards();

	goToHowToPlayActivity = new Intent(this,HowToPlayActivity.class);
	goToSettingsActivity = new Intent(this,SettingsActivity.class);
	goToAddCardActivity = new Intent(this,AddCardActivity.class);
	goToGameActivity = new Intent(this,GameActivity.class);

	
	backPress = 0;
    GameActivity.t1score = 0;
	GameActivity.t2score=0;
	typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/comesinhandy.ttf");
	typeFace1 = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Roboto-Bold.ttf");
		
		context = getApplicationContext();
		text = "";
		duration = Toast.LENGTH_LONG;
		toast = Toast.makeText(context, text, duration);
		
		
/////////////////////////////////////////////////////////////	First Use/////////////////////////////////////
	 p = PreferenceManager.getDefaultSharedPreferences(this);
		boolean firstRun = p.getBoolean("PREFERENCE_FIRST_RUN", true);
		if(firstRun)
		{
			FireMissilesDialogFragment f = new FireMissilesDialogFragment();
			f.show(getFragmentManager(),"");
		}
		p.edit().putBoolean("PREFERENCE_FIRST_RUN", false).commit();

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		exitGameButton = (Button)findViewById(R.id.exitGame);
		
		newGameButton =  (Button) findViewById(R.id.newGameButton);
		newGameButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				g = new Game(numberOfTeamCards);
						g.turn=0;
						goToGameActivity.putExtra("game", g);
						goToGameActivity.putExtra("duration", roundDuration);
						goToGameActivity.putExtra("sound", sound);
						goToGameActivity.putExtra("end", end);
						g.team1.teamName = "Team 1";
						g.team2.teamName = "Team 2";
						newGame = true;
						startActivity(goToGameActivity);
						
			}
		});
		
exitGameButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finishAffinity();
				
			}
		});
		
		
		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////Helper Methods/////////////////////////////////////////////////////////////////////

	@Override
	public void onBackPressed() {
		
		if(backPress==0)
		{
	    toasting("Press back one more time to close App");
	   backPress++;
		}	
		else if(backPress>0)
		{
		
			finishAffinity();			
	           
		}
			
		}
	
	public static void toasting(String text)
	{
		MainActivity.toast.setText(text);
		MainActivity.toast.show();
	}






public  void buildGameCards()
{
	Log.d("Building Cards","ENTERING METHOD ");
	Log.d("Building Cards","Build Code Passed"+buildCode);
	tempQ = new LinkedList<String>();
	wordCount=0;
	end=0;
	numberOfCards=0;
	numberOfTeamCards=0;
	
		Log.d("Building Cards","Normal Deck");
			try {
				    s = new Scanner(getAssets().open("wordsbank.txt"));
					s.useDelimiter("\n");
					while(s.hasNext())
					{
						tempQ.offer(s.next());
						wordCount++;
					}
				
					numberOfCards = (wordCount/5);
					  numberOfTeamCards= numberOfCards/2;
					  end = (numberOfTeamCards*2)-1;
			   } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
		            if (s != null) {
		                s.close();
		            }
		        }

	
	int m = 0;

//	Log.d("Number of cards available", ""+MainActivity.numberOfCards);
	gameCards= new Card[MainActivity.numberOfCards];
	while (tempQ.peek()!=null) {
		for(int i=0; i<MainActivity.numberOfCards;i++)
		{
			
			wrd =  tempQ.poll();
			wrd1 = tempQ.poll();
			wrd2 = tempQ.poll();
			wrd3 = tempQ.poll();
			wrd4 = tempQ.poll();
			
			Card c = new Card(wrd,wrd1,wrd2,wrd3,wrd4);
			gameCards[i]=c;
			m++;
			
		}
	//     Log.d("Created Game Cards Array","with "+m+"cards");

	}


}
public void animateIcons()
{
    final Animation infoIconAnim 	 = AnimationUtils.loadAnimation(this, R.anim.rotate);
    final Animation rateIconAnim     = AnimationUtils.loadAnimation(this, R.anim.translate_anim4);
    final Animation settingsIconAnim = AnimationUtils.loadAnimation(this, R.anim.translate_anim2);
    final Animation addIconAnim 	 = AnimationUtils.loadAnimation(this, R.anim.translate_anim3);
    
    InfoButton.startAnimation(infoIconAnim);

new CountDownTimer(100, 1000) {
        public void onFinish() {
            
        		SettingsButton.startAnimation(settingsIconAnim);
        	  new CountDownTimer(100, 1000) {
        	         public void onFinish() {
        	        	 AddCardButton.startAnimation(addIconAnim);
        	        	  new CountDownTimer(100, 1000) {
        	        	         public void onFinish() {
       	        	        	  RateButton.startAnimation(rateIconAnim);

        	        	     }

        	        	     public void onTick(long millisUntilFinished) {
        	        	              // millisUntilFinished    The amount of time until finished.
        	        	     }
        	        	   }.start();
        	     }

        	     public void onTick(long millisUntilFinished) {
        	              // millisUntilFinished    The amount of time until finished.
        	     }
        	   }.start();
        
        }

    public void onTick(long millisUntilFinished) {
             // millisUntilFinished    The amount of time until finished.
    }
  }.start();
   
    //delay
   
    //delay
  
    
}






}

