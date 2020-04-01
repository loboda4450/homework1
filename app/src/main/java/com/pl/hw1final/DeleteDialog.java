package com.pl.hw1final;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

public class DeleteDialog extends DialogFragment {
    private OnDeleteDialogInteractionListener rListener; //here

    static DeleteDialog newInstance(){
        return new DeleteDialog();
    }

    private void mListenerSetter(Context context){ //and here
        rListener = (OnDeleteDialogInteractionListener) context;
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        mListenerSetter(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.delete_question));
        builder.setPositiveButton(getString(R.string.dialog_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rListener.OnDialogPositiveClick(DeleteDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                rListener.OnDialogNegativeClick(DeleteDialog.this);
            }
        });
        return builder.create();
    }

    public interface OnDeleteDialogInteractionListener{
        void OnDialogPositiveClick(DeleteDialog dialog);
        void OnDialogNegativeClick(DeleteDialog dialog);
    }
}
