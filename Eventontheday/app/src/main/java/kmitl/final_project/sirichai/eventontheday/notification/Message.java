package kmitl.final_project.sirichai.eventontheday.notification;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import kmitl.final_project.sirichai.eventontheday.MainActivity;

/**
 * Created by atomiz on 12/11/2560.
 */

public class Message {
    public void displayMessage(int resultCode, Bundle resultData){
        String message = resultData.getString("message");
        //Toast.makeText(MainActivity.this, resultCode + " " + message, Toast.LENGTH_SHORT).show();
        Log.i("message", resultCode + " " + message);
    }
}
