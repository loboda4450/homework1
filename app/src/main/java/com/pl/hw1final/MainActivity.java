package com.pl.hw1final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pl.hw1final.persons.PersonListContent;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements PersonFragment.onListFragmentInteraction, CallDialog.OnCallDialogInteractionListener, DeleteDialog.OnDeleteDialogInteractionListener {
    public static final String taskExtra = "taskExtra";
    private int currentItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddPersonActivity(getApplicationContext());
            }
        });
    }

    private void startAddPersonActivity(Context context){
        Toast.makeText(context, getString(R.string.new_person_add), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddPerson.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            ((PersonFragment) getSupportFragmentManager().findFragmentById(R.id.personFragment)).notifyDataChange();

        }
    }

    private void startPersonInfoActivity(PersonListContent.Person person, int position){
        Intent intent = new Intent(this, PersonInfoActivity.class);
        intent.putExtra(taskExtra, person);
        startActivity(intent);
    }

    @Override
    public void onListFragmentClickInteraction(PersonListContent.Person person, int position) {
        Toast.makeText(this, getString(R.string.item_selected_msg), Toast.LENGTH_SHORT).show();
        startPersonInfoActivity(person, position);
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        Toast.makeText(this, "It works :3", Toast.LENGTH_SHORT).show();
        showCallDialog();
    }

    @Override
    public void onImageButtonClickInteraction(int position){
        Toast.makeText(this, "Delete works .-.", Toast.LENGTH_SHORT).show();
        showDeleteDialog();
    }

    private void showCallDialog(){
        CallDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.call_dialog_tag));
    }

    private void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }

    @Override
    public void OnDialogPositiveClick(CallDialog dialog) {

    }

    @Override
    public void OnDialogNegativeClick(CallDialog dialog) {

    }

    @Override
    public void OnDialogPositiveClick(DeleteDialog dialog) {
        if (currentItemPosition != -1 && currentItemPosition < PersonListContent.ITEMS.size()) {
            PersonListContent.removeItem(currentItemPosition);
            ((PersonFragment) getSupportFragmentManager().findFragmentById(R.id.personFragment)).notifyDataChange();
        }
    }

    @Override
    public void OnDialogNegativeClick(DeleteDialog dialog) {

    }
}
