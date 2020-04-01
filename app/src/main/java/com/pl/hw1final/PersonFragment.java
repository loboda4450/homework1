package com.pl.hw1final;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pl.hw1final.persons.PersonListContent;
import com.pl.hw1final.persons.PersonListContent.Person;

public class PersonFragment extends Fragment {
    private MyPersonRecyclerViewAdapter myRecyclerViewAdapter;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private onListFragmentInteraction mListener;

    public PersonFragment() {
    }


    public static PersonFragment newInstance(int columnCount) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_list, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager((new LinearLayoutManager(context)));
            myRecyclerViewAdapter = new MyPersonRecyclerViewAdapter(PersonListContent.ITEMS, mListener);
            recyclerView.setAdapter(myRecyclerViewAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onListFragmentInteraction) {
            mListener = (onListFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void notifyDataChange(){
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    public interface onListFragmentInteraction {
        void onListFragmentClickInteraction(Person person, int position);
        void onListFragmentLongClickInteraction(int position);
    }
}
