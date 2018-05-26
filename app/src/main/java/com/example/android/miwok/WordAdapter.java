package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();


    /**
     * init adapter. Resource point to given id or, as in this case any value as this is a custom implementation
     * @param context
     * @param words
     */
    public WordAdapter(Activity context, ArrayList<Word> words){
        super(context, 0, words);
    }

    /**
     * Create view
     * @param position - list item index position
     * @param convertView - the recycled view to populate
     * @param parent
     * @return - view item by index position
     */
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false
            );
        }

        // get item indes position
        Word currentWord = getItem(position);

        // find Miwok view in XML
        TextView numbersMTextView = listItemView.findViewById(R.id.miwok_word);
        //get Miwok translation
        numbersMTextView.setText(currentWord.getMiwokTransition());

        // find English translation in XML
        TextView numbersETextView = listItemView.findViewById(R.id.english_word);
        // get English translsation
        numbersETextView.setText(currentWord.getDefaultTranslation());

        // find imageview in XML
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // get the corresponding image
        //imageView.setImageResource(currentWord.getImageResourceId());
        if (currentWord.hasImage()){

            //if image provided
            imageView.setImageResource(currentWord.getImageResourceId());
            //set img to VISIBLE
            imageView.setVisibility(View.VISIBLE);
        } else {
            // if on image provided
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
