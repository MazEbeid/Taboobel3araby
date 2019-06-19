package mazenebeidcreations.games.taboobel3araby;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class FireMissilesDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("أهلا وسهلا ");
        builder.setMessage("لو أول مره تلعب وعايز مساعده ، دوس على ال !")
               .setPositiveButton("شكراً", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       
                   }
               
               
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}