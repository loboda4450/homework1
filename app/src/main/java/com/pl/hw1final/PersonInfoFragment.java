package com.pl.hw1final;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pl.hw1final.persons.PersonListContent;

import org.w3c.dom.Text;

public class PersonInfoFragment extends Fragment {

    public PersonInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Intent intent = getActivity().getIntent();
        if(intent != null){
            PersonListContent.Person receivedPerson = intent.getParcelableExtra(MainActivity.taskExtra);
            if(receivedPerson != null){
                displayPerson(receivedPerson);
            }
        }
    }

    public void displayPerson(PersonListContent.Person person) {
        FragmentActivity activity = getActivity();

        TextView taskInfoName = activity.findViewById(R.id.taskInfoName);
        TextView taskInfoBirthday = activity.findViewById(R.id.taskInfoBirthday);
        ImageView taskInfoImage = activity.findViewById(R.id.taskInfoImage);
        TextView taskInfoPhone = activity.findViewById(R.id.taskInfoPhone);
        String name = person.name + " " + person.surname;

        taskInfoName.setText(name);
        taskInfoBirthday.setText(person.birthday);
        taskInfoPhone.setText(person.phone);
        if (person.picPath.contains("drawable")) {
            Drawable taskDrawable;
            switch (person.picPath) {
                case "drawable 1":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_1);
                    break;
                case "drawable 2":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_2);
                    break;
                case "drawable 3":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_3);
                    break;
                case "drawable 4":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_4);
                    break;
                case "drawable 5":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_5);
                    break;
                case "drawable 6":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_6);
                    break;
                case "drawable 7":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_7);
                    break;
                case "drawable 8":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_8);
                    break;
                case "drawable 9":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_9);
                    break;
                case "drawable 10":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_10);
                    break;
                case "drawable 11":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_11);
                    break;
                case "drawable 12":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_12);
                    break;
                case "drawable 13":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_13);
                    break;
                case "drawable 14":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_14);
                    break;
                case "drawable 15":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_15);
                    break;
                case "drawable 16":
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_16);
                    break;
                default:
                    taskDrawable = activity.getResources().getDrawable(R.drawable.avatar_16);
            }
            taskInfoImage.setImageDrawable(taskDrawable);
        } else {
            taskInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.avatar_1));
        }
    }
}
