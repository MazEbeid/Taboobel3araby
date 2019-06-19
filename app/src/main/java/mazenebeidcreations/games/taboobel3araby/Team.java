package mazenebeidcreations.games.taboobel3araby;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import android.util.Log;

public class Team {
	
	  Card [] teamCards;
	  Queue<Card>teamCardsQ;
	  String teamName;

	public Team (int numberOfTeamCards)
	{
		teamCardsQ = new LinkedList<Card>();
		teamCards = new Card [10];
		Log.d("Creating","Team");

}
	
}
