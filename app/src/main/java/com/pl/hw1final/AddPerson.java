package com.pl.hw1final;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.pl.hw1final.persons.PersonListContent;
import java.util.Calendar;

public class AddPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        final EditText personBirthdayEditText = findViewById(R.id.person_birthday);
        Button adder = findViewById(R.id.addButton);
        adder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = 9;
                EditText personNameEditText = findViewById(R.id.person_name);
                EditText personSurnameEditText = findViewById(R.id.person_surname);
                EditText personPhoneEditText = findViewById(R.id.person_phone);
                EditText personBirthdayEditText = findViewById(R.id.person_birthday);
                Spinner drawableSpinner = findViewById(R.id.drawableSpinner);
                String personName = personNameEditText.getText().toString();
                String personSurname = personSurnameEditText.getText().toString();
                String personPhone = personPhoneEditText.getText().toString();
                String personBirthday = personBirthdayEditText.getText().toString();
                String selectedImage = drawableSpinner.getSelectedItem().toString();

                if(personPhone.contains("+")) {
                    len = 12;
                }

                if (personName.isEmpty()) {
                    personName = getString(R.string.default_name);
                }

                if (personSurname.isEmpty()) {
                    personSurname = getString(R.string.default_surname);
                }

                if (personPhone.isEmpty()) {
                    personPhone = getString(R.string.default_phone);
                    personPhoneEditText.setText(personPhone);
                }

                if (personBirthday.isEmpty()) {
                    personBirthday = getString(R.string.default_birthday);
                }

                if (!Patterns.PHONE.matcher(personPhone).matches() || personPhone.length() != len) {
                    Toast.makeText(getApplicationContext(), "Phone number contains letters or is not " + len + " digit long", Toast.LENGTH_LONG).show();
                } else {
                    PersonListContent.addItem(new PersonListContent.Person("Person" + PersonListContent.ITEMS.size() + 1,
                            personName,
                            personSurname,
                            personPhone,
                            personBirthday,
                            selectedImage));

                    personNameEditText.setText("");
                    personSurnameEditText.setText("");
                    personPhoneEditText.setText("");

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }

                    setResult(RESULT_OK);
                    finish();
                    }
                }
        });

        personBirthdayEditText.setOnClickListener(new View.OnClickListener() {
            EditText personBirthday = findViewById(R.id.person_birthday);
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog picker = new DatePickerDialog(AddPerson.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                personBirthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }
}