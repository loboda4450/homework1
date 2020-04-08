package com.pl.hw1final.persons;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonListContent {
    public static final List<Person> ITEMS = new ArrayList<Person>();
    public static final Map<String, Person> ITEM_MAP = new HashMap<String, Person>();

    private static final int COUNT = 5;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createPerson(i));
        }
    }

    public static void addItem(Person item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void removeItem(int position){
        String itemId = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }

    public static Person getItem(int position){
        if (position >= ITEMS.size()) throw new AssertionError();
        return ITEMS.get(position);
    }

    private static Person createPerson(int position) {
        return new Person(String.valueOf(position),
                "Adam",
                "Nowak",
                "1234567890",
                "01/01/1970");
    }

    public static class Person implements Parcelable {
        public final String id;
        public final String name;
        public final String surname;
        public final String phone;
        public final String birthday;
        public final String picPath;

        public Person(String id, String name, String surname, String phone, String birthday, String picPath) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.phone = phone;
            this.birthday = birthday;
            this.picPath = picPath;
        }

        public Person(String id, String name, String surname, String phone, String birthday) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.phone = phone;
            this.birthday = birthday;
            this.picPath = "";
        }

        protected Person(Parcel in) {
            id = in.readString();
            name = in.readString();
            surname = in.readString();
            phone = in.readString();
            birthday = in.readString();
            picPath = in.readString();
        }

        public static final Creator<Person> CREATOR = new Creator<Person>() {
            @Override
            public Person createFromParcel(Parcel in) {
                return new Person(in);
            }

            @Override
            public Person[] newArray(int size) {
                return new Person[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeString(surname);
            dest.writeString(phone);
            dest.writeString(birthday);
            dest.writeString(picPath);
        }
    }
}
