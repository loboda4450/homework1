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

    private OnDeleteDialogInteractionListener mListener;

    public DeleteDialog(){

    }

    static DeleteDialog newInstance(){
        return new DeleteDialog();
    }


    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.delete_question));
        builder.setPositiveButton(getString(R.string.dialog_confirm), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.OnDialogPositiveClick(DeleteDialog.this);
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.OnDialogNegativeClick(DeleteDialog.this);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDeleteDialogInteractionListener) {
            mListener = (OnDeleteDialogInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDeleteDialogInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnDeleteDialogInteractionListener{
        void OnDialogPositiveClick(DeleteDialog dialog);
        void OnDialogNegativeClick(DeleteDialog dialog);
    }
}
