package com.pl.hw1final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.pl.hw1final.persons.PersonListContent;

import java.util.Objects;
import java.util.regex.Pattern;

public class AddPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
    }

    public void addButton(View view) {
        EditText personNameEditText = findViewById(R.id.person_name);
        EditText personSurnameEditText = findViewById(R.id.person_surname);
        EditText personPhoneEditText = findViewById(R.id.person_phone);
        EditText personBirthdayEditText = findViewById(R.id.person_birthday);
        Spinner drawableSpinner = findViewById(R.id.drawableSpinner);
        String personName = personNameEditText.getText().toString();
        String personSurname = personSurnameEditText.getText().toString();
        String personPhone = personSurnameEditText.getText().toString();
        String personBirthday = personBirthdayEditText.getText().toString();
        String selectedImage = drawableSpinner.getSelectedItem().toString();

        if (personName.isEmpty() && personSurname.isEmpty() && personPhone.isEmpty() && personBirthday.isEmpty()) {
            PersonListContent.addItem(new PersonListContent.Person(
                    "Task" + PersonListContent.ITEMS.size() + 1,
                    getString(R.string.default_name),
                    getString(R.string.default_surname),
                    getString(R.string.default_phone),
                    getString(R.string.default_birthday),
                    selectedImage));
        } else {
            if (personName.isEmpty()) {
                personName = getString(R.string.default_name);
            }
            if (personSurname.isEmpty()) {
                personSurname = getString(R.string.default_surname);
            }
            if (personPhone.isEmpty()) {
                personPhone = getString(R.string.default_phone);
            }
            if (personBirthday.isEmpty()) {
                personBirthday = getString(R.string.default_birthday);
            }
//            if (Pattern.matches("[a-zA-Z]+", personPhone) || personPhone.length() != 9) {
//                Snackbar.make(view, "Phone number include letters or is not 9 number long", BaseTransientBottomBar.LENGTH_SHORT);
//            } else {
            PersonListContent.addItem(new PersonListContent.Person("Task" + PersonListContent.ITEMS.size() + 1,
                    personName,
                    personSurname,
                    personPhone,
                    personBirthday,
                    selectedImage));
//            }
        }

        ((PersonFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.personFragment))).notifyDataChange();
        personNameEditText.setText("");
        personSurnameEditText.setText("");
        personPhoneEditText.setText("");
        personBirthdayEditText.setText("");

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        setResult(RESULT_OK);
        finish();
    }
}
