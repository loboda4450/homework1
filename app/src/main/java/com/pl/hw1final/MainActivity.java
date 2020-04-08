package com.pl.hw1final;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pl.hw1final.persons.PersonListContent;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements PersonFragment.onListFragmentInteraction, CallDialog.OnCallDialogInteractionListener, DeleteDialog.OnDeleteDialogInteractionListener {
    public static final String taskExtra = "taskExtra";
    private int currentItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddPersonActivity(v);
            }
        });
    }

    private void displayPersonInFragment(PersonListContent.Person person){
        PersonInfoFragment personInfoFragment = ((PersonInfoFragment) getSupportFragmentManager().findFragmentById(R.id.personInfoFragment));
        if(personInfoFragment != null){
            personInfoFragment.displayPerson(person);
        }
    }

    public void startAddPersonActivity(View view){
        Intent intent = new Intent(this, AddPerson.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            ((PersonFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.personFragment))).notifyDataChange();
        }
    }


    private void startPersonInfoActivity(PersonListContent.Person person, int position){
        Intent intent = new Intent(this, PersonInfoActivity.class);
        intent.putExtra(taskExtra, person);
        startActivity(intent);
    }

    @Override
    public void onListFragmentClickInteraction(PersonListContent.Person person, int position) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayPersonInFragment(person);
        } else {
            Toast.makeText(this, getString(R.string.item_selected_msg), Toast.LENGTH_SHORT).show();
            startPersonInfoActivity(person, position);
        }
    }

    @Override
    public void onListFragmentLongClickInteraction(int position) {
        showCallDialog();
    }

    @Override
    public void onImageButtonClickInteraction(int position){
        showDeleteDialog();
        currentItemPosition = position;
    }

    private void showCallDialog(){
        CallDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.call_dialog_tag));
    }

    private void showDeleteDialog(){
        DeleteDialog.newInstance().show(getSupportFragmentManager(), getString(R.string.delete_dialog_tag));
    }

    @Override
    public void OnDialogPositiveClick(CallDialog dialog) {
        Toast.makeText(this, "Calling", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnDialogNegativeClick(CallDialog dialog) {
        Toast.makeText(this, "Call canceled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnDialogPositiveClick(DeleteDialog dialog) {
        if (currentItemPosition != -1 && currentItemPosition < PersonListContent.ITEMS.size()) {
            PersonListContent.removeItem(currentItemPosition);
            ((PersonFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.personFragment))).notifyDataChange();
        }
    }

    @Override
    public void OnDialogNegativeClick(DeleteDialog dialog) {
        View v = findViewById(R.id.delete_button);

        if(v != null){
            Snackbar.make(v, getString(R.string.delete_cancel_msg), Snackbar.LENGTH_SHORT)
                    .setAction(getString(R.string.retry_msg), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showDeleteDialog();
                        }
                    }).show();
        }
    }


}
