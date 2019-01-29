package character.component.com.characterexplorer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.TextView;

public class ViewUtils {

    public static void hasValidText(Context context, String msg, TextView tv) {
        if (!TextUtils.isEmpty(msg)) {
            tv.setText(msg);
        } else {
            tv.setText(context.getResources().getString(R.string.character_no_info));
        }
    }

    public static void redirectToExternalLink(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            //
        }
    }
}
