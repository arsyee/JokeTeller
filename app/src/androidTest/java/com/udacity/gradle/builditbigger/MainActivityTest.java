package com.udacity.gradle.builditbigger;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.udacity.gradle.jokeshower.JokeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;


public class MainActivityTest {

    @Rule
    public final IntentsTestRule<MainActivity> mIntentsRule =
            new IntentsTestRule(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(mIntentsRule.getActivity().countingIdlingResource);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(mIntentsRule.getActivity().countingIdlingResource);
    }

    @Test
    public void asyncTask_retrievesJoke() {
        new MainActivity.EndpointsAsyncTask(mIntentsRule.getActivity()).execute();
        intended(hasComponent(JokeActivity.class.getName()));
        intended(hasExtra(is(JokeActivity.JOKE_EXTRA), not(isEmptyOrNullString())));
    }
}