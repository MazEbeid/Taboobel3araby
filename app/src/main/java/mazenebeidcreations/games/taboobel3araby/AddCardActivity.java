package mazenebeidcreations.games.taboobel3araby;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddCardActivity extends Activity {
	static Button saveCardButton, addAnotherCardButton;
	static EditText wordEditText, word1EditText, word2EditText, word3EditText, word4EditText;
	static String cardBits;
	static Intent goToMainActivity;
	static int newCardNumber = 1;
	static TextView newCardNumberLabel;
	static String [] address;
	DatabaseReference addCardDBRef;
	DatabaseReference numberOfAddedCardsRef;
	int addedCardNumber;

@Override
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_card_activity);
		address = new String []{"mebeidcreations@gmail.com"};
	addCardDBRef =FirebaseDatabase.getInstance().getReference("Added Cards");
	numberOfAddedCardsRef =FirebaseDatabase.getInstance().getReference("Number of Cards").child("Added Cards Number");


	numberOfAddedCardsRef.addListenerForSingleValueEvent(new ValueEventListener() {
		@Override
		public void onDataChange(DataSnapshot dataSnapshot) {

			if(dataSnapshot.getValue()==null) //we are testing to see if there is a value already savedd for
			                                  // the number of added cards in the database
			{
				numberOfAddedCardsRef.setValue(""+0);  //if there are no values saved then we add a 0 to the child
				addedCardNumber = 0; 			      // the addedCardNumber is an int value of the data saved in the databas
				Log.d("ADDED_CARDS",""+addedCardNumber);

			}
			else
			{
				addedCardNumber = Integer.parseInt(String.valueOf(dataSnapshot.getValue())); //if there is a value for added card number then we
																							 // retrieve it and assign it to addedCardNumber
				Log.d("ADDED_CARDS",""+addedCardNumber);

			}
		}

		@Override
		public void onCancelled(DatabaseError databaseError) {

		}
	});




	saveCardButton = (Button)findViewById(R.id.saveCardButton);
		addAnotherCardButton = (Button)findViewById(R.id.addAnotherCard);
		wordEditText = (EditText)findViewById(R.id.wordEditText);
		word1EditText = (EditText)findViewById(R.id.word1EditText);
		word2EditText = (EditText)findViewById(R.id.word2EditText);
		word3EditText = (EditText)findViewById(R.id.word3EditText);
		word4EditText = (EditText)findViewById(R.id.word4EditText);
		newCardNumberLabel = (TextView)findViewById(R.id.newCardNumber);

		word1EditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				word1EditText.setBackgroundResource(R.drawable.edit_text_layout);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});



	word2EditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				word2EditText.setBackgroundResource(R.drawable.edit_text_layout);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});

	word3EditText.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			word3EditText.setBackgroundResource(R.drawable.edit_text_layout);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	});
	
	word4EditText.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			word4EditText.setBackgroundResource(R.drawable.edit_text_layout);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	});
	
	wordEditText.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			wordEditText.setBackgroundResource(R.drawable.word_edit_box);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	});



	
	
		
		
		goToMainActivity = new Intent(this,MainActivity.class);


		
		
		cardBits = "";

		
		saveCardButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				cardBits = cardBits + getCardBits();


				if (cardFieldsAreEmpty() && cardBits.isEmpty()) {

					MainActivity.toasting("Card can't contain empty words" + cardBits);
					highlightEmptyFields();

				} else {


					String word = wordEditText.getText().toString().trim();
					String word1 = word1EditText.getText().toString().trim();
					String word2 = word2EditText.getText().toString().trim();
					String word3 = word3EditText.getText().toString().trim();
					String word4 = word4EditText.getText().toString().trim();

					cardDB card = new cardDB(word, word1, word2, word3, word4); //create a card
					addCardDBRef.child(""+addedCardNumber).setValue(card); //give its node a name in this case we are using the addedcardnumber
					addedCardNumber++;										//increment the addcardnumber by 1 so we can have a unique name next time we add a card
					numberOfAddedCardsRef.setValue(""+addedCardNumber);    //update the number of added cards in the database so next time when we add a card we read this value first as explained in the code at the begining of this class
					Toast.makeText(getApplicationContext(), "Card added, thanks!!", Toast.LENGTH_SHORT).show(); // toast to the user that the card was added

					// now I need you to add an alert dialog asking the user lw 3ayz yzawd another card if he says yes then the text fields should be cleared
					// to prepare fot the next card entry
					//if the user says no then the activity should end and you back to the main activity
					//SHOKRAN M3a EL SALAMA
				}
			}
		});



		addAnotherCardButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				cardBits=cardBits+getCardBits();
				
				if(cardFieldsAreEmpty())
				{
					MainActivity.toasting("Card can't contain empty words");
					highlightEmptyFields();
			
				}
				else
				{
				//	MainActivity.toasting("You have entered "+cardBits);
					newCardNumber++;
					newCardNumberLabel.setText(""+newCardNumber);
					
					resetCardFields();
				}
				
				
			}
		});


}

public static void resetCardFields()
{
	wordEditText.setText(null);
	word1EditText.setText(null);
	word2EditText.setText(null);
	word3EditText.setText(null);
	word4EditText.setText(null);
}

public static String getCardBits()
{
	String card="";
	if(cardFieldsAreEmpty())
	{
		MainActivity.toasting("Card can't contain empty words");
		return card;
		
	}
	
	else
	{
	//	MainActivity.toasting("getting bits");
		card = wordEditText.getText().toString()+
				   '\n'+
				   word1EditText.getText().toString()+
				   '\n'+
				   word2EditText.getText().toString()+
				   '\n'+
				   word3EditText.getText().toString()+
				   '\n'+
				   word4EditText.getText().toString()+
				   '\n'+
				   "-----------------"+
				   '\n';
		
		//MainActivity.toasting(cardBits);
		return card;
	
	}
}

public static boolean cardFieldsAreEmpty()
{
	
	
	if(wordEditText.getText().toString().isEmpty()
			||word1EditText.getText().toString().isEmpty()
			||word2EditText.getText().toString().isEmpty()
			||word3EditText.getText().toString().isEmpty()
			||word4EditText.getText().toString().isEmpty())
	{
		return true;
	}
	else
	{
		
		return false;
	}
}

public static void highlightEmptyFields()
{
	
	
	if(word1EditText.getText().toString().isEmpty())
	{
		word1EditText.setBackgroundResource(R.drawable.active_edit_text_layout);
		

	}
	if(word2EditText.getText().toString().isEmpty())
	{
		word2EditText.setBackgroundResource(R.drawable.active_edit_text_layout);
		

	}
	if(word3EditText.getText().toString().isEmpty())
	{
		word3EditText.setBackgroundResource(R.drawable.active_edit_text_layout);
	

	}
	if(word4EditText.getText().toString().isEmpty())
	{
		word4EditText.setBackgroundResource(R.drawable.active_edit_text_layout);
	

	}
	if(wordEditText.getText().toString().isEmpty())
	{
		wordEditText.setBackgroundResource(R.drawable.active_edit_text_layout);
	

	}
		
	
}

	@Override
	public void onBackPressed() {
		startActivity(goToMainActivity);
		finish();
	}





}












