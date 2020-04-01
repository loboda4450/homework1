package com.pl.hw1final;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.pl.hw1final.PersonFragment.onListFragmentInteraction;
import com.pl.hw1final.persons.PersonListContent.Person;

import java.util.List;
public class MyPersonRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonRecyclerViewAdapter.ViewHolder> {

    private final List<Person> mValues;
    private final onListFragmentInteraction mListener;

    public MyPersonRecyclerViewAdapter(List<Person> items, onListFragmentInteraction listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Person person = mValues.get(position);
        holder.mItem = person;
        holder.mContentView.setText(person.name);
        final String picPath = person.picPath;
        Context context = holder.mView.getContext();
        if (picPath != null && !picPath.isEmpty()) {
            if (picPath.contains("drawable")) {
                Drawable taskDrawable;
                switch (picPath) {
                    case "drawable 1":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_1);
                        break;
                    case "drawable 2":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_2);
                        break;
                    case "drawable 3":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_3);
                        break;
                    case "drawable 4":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_4);
                        break;
                    case "drawable 5":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_5);
                        break;
                    case "drawable 6":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_6);
                        break;
                    case "drawable 7":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_7);
                        break;
                    case "drawable 8":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_8);
                        break;
                    case "drawable 9":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_9);
                        break;
                    case "drawable 10":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_10);
                        break;
                    case "drawable 11":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_11);
                        break;
                    case "drawable 12":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_12);
                        break;
                    case "drawable 13":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_13);
                        break;
                    case "drawable 14":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_14);
                        break;
                    case "drawable 15":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_15);
                        break;
                    case "drawable 16":
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_16);
                        break;
                    default:
                        taskDrawable = context.getResources().getDrawable(R.drawable.avatar_16);
                }
                holder.mItemImageView.setImageDrawable(taskDrawable);
            }
        } else {
            holder.mItemImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.avatar_1));
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentClickInteraction(holder.mItem, position);
                }
            }
        });

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mListener.onListFragmentLongClickInteraction(position);
                return false;
            }
        });

        holder.mView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onImageButtonClickInteraction(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mItemImageView;
        public Person mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.content);
            mItemImageView = view.findViewById(R.id.item_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
