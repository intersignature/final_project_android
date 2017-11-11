package kmitl.final_project.sirichai.eventontheday.notification;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by atomiz on 12/11/2560.
 */

@SuppressLint("ParcelCreator")
public class MessageReceiver extends ResultReceiver {
    private Message message;

    public MessageReceiver(Message message) {
        super(new Handler());
        this.message = message;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        message.displayMessage(resultCode, resultData);
    }
}
