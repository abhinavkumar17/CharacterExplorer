package character.component.com.characterexplorer.screens.common.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import character.component.com.characterexplorer.R;

public class ServerErrorDialogFragment extends DialogFragment {

    public static ServerErrorDialogFragment newInstance() {
        return new ServerErrorDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(R.string.server_error_dialog_title);
        alertDialogBuilder.setMessage(R.string.server_error_dialog_message);
        alertDialogBuilder.setPositiveButton(
                R.string.server_error_dialog_button_caption,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }
        );
        return alertDialogBuilder.create();
    }
}
