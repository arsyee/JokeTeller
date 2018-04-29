package com.udacity.gradle.jokeshower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        if (getIntent() != null && getIntent().hasExtra(JOKE_EXTRA)) {
            ((TextView) findViewById(R.id.tv_joke)).setText(getIntent().getStringExtra(JOKE_EXTRA));
        }
    }
}
