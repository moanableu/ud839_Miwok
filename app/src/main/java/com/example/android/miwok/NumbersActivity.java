/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.list;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // final added because of variable scope
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        // instantiate a WordAdapter that with data source on the ArrayList
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // link ListView to XML in this case rootView is linked to numbers.XML
        ListView listView = findViewById(R.id.list);

        //set the listView to use the adapter
        listView.setAdapter(adapter);

        // associate an OnClickListener to the array
        // onClickListener can only reference vars is they are declared final
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                //get access to array index position and store it in a var
                Word word = words.get(position);

                Log.v("NumbersActivity", "current word: " + word);
                // context is needed to eliminate ambiguity
                // use the getAudioResourceId method on local var for position
               mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
               mediaPlayer.start();
               //call the local onRelease method
               mediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });
    }

    //override onCompletion member variable, and call a release method on it
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    /**
     * good practice to avoid battery consumption
     */
    private void releaseMediaPlayer(){
        // if not null it may be in use
        if (mediaPlayer != null){
            //if not in use
            mediaPlayer.release();

            // use this change in state to signal that the media pl;ayer is not in use
            mediaPlayer = null;
        }
    }
}
