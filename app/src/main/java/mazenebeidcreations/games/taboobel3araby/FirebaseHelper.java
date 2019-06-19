package mazenebeidcreations.games.taboobel3araby;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Soly on 08/11/2017.
 */

public class FirebaseHelper {


    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference ref = database.getReference();


    public static void AddCard(String location,cardDB card) {

        ref.child(location).setValue(card);

    }

}
