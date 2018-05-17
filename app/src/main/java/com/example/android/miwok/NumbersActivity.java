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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.rootView;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> numbers = new ArrayList<String>();
        numbers.add("one");
        numbers.add("two");
        numbers.add("three");
        numbers.add("four");
        numbers.add("five");
        numbers.add("six");
        numbers.add("seven");
        numbers.add("eight");
        numbers.add("nine");
        numbers.add("ten");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);

        ListView listView = findViewById(rootView);

        listView.setAdapter(itemsAdapter);

       /*Log.v("NumbersActivity", "numbers array at index 0 " + numbers.get(0));

        dynamically create view by looping thru a list
        LinearLayout rootView = findViewById(R.id.rootView);

        int index;

        int index = 0;

            while (index < numbers.size()){
            //Log.v("NumbersActivity", "Index" + index + "Value: "+ numbers.get(index));
            //create new text view, important: context!
            TextView numbersView = new TextView(this);

            // set the text:
            numbersView.setText(numbers.get(index));

            //add view to parent layout
            rootView.addView(numbersView);

            index++;
        }
            for (index = 0; index < numbers.size(); index++){
            TextView numbersView = new TextView(this);
            numbersView.setText(numbers.get(index));
            rootView.addView(numbersView);
        }*/



    }
}
