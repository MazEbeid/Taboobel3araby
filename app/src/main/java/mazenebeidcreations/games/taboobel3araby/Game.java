package mazenebeidcreations.games.taboobel3araby;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import android.util.Log;

public class  Game implements Serializable {
	
	static boolean status;
	static int turn=0;
	static Team team1;
	static Team team2;
	static Card c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
	static Queue<Card> gameCards;
	static int numberOfCards;

	  
	
	public Game(int numberOfTeamCards)
	{
	 	gameCards = new LinkedList<Card>();
	 	team1 = new Team(numberOfTeamCards);
		team2 = new Team(numberOfTeamCards);
		
	}

	public static void generaTeamCards (Game g, int numberOfTeamCards) throws IOException
	{
		//c1 = new Card("أبو تريكه","L3eeb Kora", "El Ahly", "Mesh Ikhwan", "22");
	//	c2 = new Card("هاححح","Ahmed Moussa", "i3lamy", "TV", "M3rs");
	//	c3 = new Card("Mof3el nawaawy","hanmout mnfreen", "Mashrou3", "El Dab3a", "Russia");
	//	c4 = new Card("Hany Shaker","Moghany", "7asas el geel", "masry", "mtkhzw2");
	//	c5 = new Card("shakira","Piquet", "Its time for africa", "Kazem", "Blonde");
	//	c6 = new Card("Nadia El Guindy","Momasela", "Imra2ah Hazet 3rsh Masr", "Tel Abeeb", "Masryah");
	///	c7 = new Card("Makarona Bel White Sauce","Akl", "Carbs", "Italian", "Penne");
//		c8 = new Card("Goal Magdy 3bd El Ghany","Kora", "Kas El 3alam", "masry", "penalty");
	//	c9 = new Card("shisha","dokhan", "smoking", "m3asel", "tofa7");
//		c10 = new Card("Madinet Nasr","3bass El3akkad", "Makram Ebeid", "Za7ma", "Share3 el nasr");
		
	//	gameCards = new LinkedList<Card>();
//		gameCards.add(c1);
//		gameCards.add(c2);
//		gameCards.add(c3);
//		gameCards.add(c4);
//		gameCards.add(c5);
//		gameCards.add(c6);
//		gameCards.add(c7);
	//	gameCards.add(c8);
//		gameCards.add(c9);
//		gameCards.add(c10);
		
		
		
		for(int i=0;i<=numberOfTeamCards-1;i++)
	{
			//
			if(g.gameCards.peek()!=null)
			{
				team1.teamCards[i]=g.gameCards.poll();
				Log.d("Team 1 Cards",team1.teamCards[i].word);
			}
    }
		
		for(int i=0;i<=numberOfTeamCards-1;i++)
		{
			
				if(gameCards.peek()!=null)
				{
					team2.teamCards[i]=g.gameCards.poll();
			
					Log.d("Team 2 Cards",team2.teamCards[i].word);
					
				}
	    
		}
	
		
	    }
	}

