package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {
    // Required empty public constructor
    private MediaPlayer mediaPlayer;
    private AudioManager am;

    // instantiate AudioMgr.OnAudioFocusChangeListener to handle various steps
    private AudioManager.OnAudioFocusChangeListener mOnAudioChange =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        // add playback
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private void releaseMediaPlayer(){
        // if not null it may be in use
        if (mediaPlayer != null){
            //if not in use
            mediaPlayer.release();

            // use this change in state to signal that the media player is not in use
            mediaPlayer = null;

            am.abandonAudioFocus(mOnAudioChange);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();}

    public NumbersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Fix the error by getting the Activity object instance first.
        // This is the Activity that encloses the current Fragment, which will be the NumbersActivity for the NumbersFragment.
        // Then call getSystemService(String) on that Activity object.
        // add getActivity() then call '.getSystemService(Context.AUDIO_SERVICE);'
        am = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

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
        // Fix the error by passing in a reference to the Activity that encloses this Fragment as the context.
        // getActivity() as opposed to 'this'
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        // link ListView to XML in this case rootView is linked to numbers.XML
        // Fix the error by calling findViewById(int) on the rootView object,
        // which should contain children views such as the ListView.
        // cast the ListView then add 'rootView' and apply the findViewById method to it
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        //set the listView to use the adapter
        listView.setAdapter(adapter);

        // associate an OnClickListener to the array
        // onClickListener can only reference vars is they are declared final
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {

                releaseMediaPlayer();
                //get access to array index position and store it in a var
                Word word = words.get(position);

                //request audio focus
                int result = am.requestAudioFocus(mOnAudioChange,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                //audio focus gained
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Log.v("NumbersActivity", "current word: " + word);
                    // context is needed to eliminate ambiguity
                    // use the getAudioResourceId method on local var for position
                    // Fix the error by passing in the activity for the first input parameter.
                    // getActivity()
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());
                    mediaPlayer.start();
                    //call the local onRelease method
                    mediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

       return rootView;

    }

}
