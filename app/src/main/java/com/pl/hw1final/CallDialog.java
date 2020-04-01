package com.pl.hw1final;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class CallDialog extends DialogFragment {
    private OnCallDialogInteractionListener mListener;

    static CallDialog newInstance(){
        return new CallDialog();
    }

    private void mListenerSetter(Context context){
        mListener = (OnCallDialogInteractionListener) context;
    }


    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        mListenerSetter(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.call_question));
        builder.setPositiveButton(getString(R.string.dialog_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.OnDialogPositiveClick(CallDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.OnDialogNegativeClick(CallDialog.this);
            }
        });
        return builder.create();
    }

    public interface OnCallDialogInteractionListener{
        void OnDialogPositiveClick(DialogFragment dialog);
        void OnDialogNegativeClick(DialogFragment dialog);
    }
}
