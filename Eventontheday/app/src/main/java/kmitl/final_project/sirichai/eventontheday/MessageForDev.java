package kmitl.final_project.sirichai.eventontheday;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by atomiz on 8/11/2560.
 */

public class MessageForDev {
    public void Log(String message){
        Log.i("DRINK", message);
    }
    public void Toast(Context context, String message){
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
    }
}
