package mazenebeidcreations.games.taboobel3araby;

import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Soly on 06/11/2017.
 */

public class cardDB {



    String word;
    String word1;
    String word2;
    String word3;
    String word4;

public cardDB(){

}
public cardDB(String word,String word1,String word2,String word3,String word4){

    this.word=word;
    this.word1=word1;
    this.word2=word2;
    this.word3=word3;
    this.word4=word4;
}
    public String getword() {
        return word;
    }

    public String getword1() {
        return word1;
    }

    public String getword2() {
        return word2;
    }

    public String getword3() {
        return word3;
    }

    public String getword4() {
        return word4;
    }



}
