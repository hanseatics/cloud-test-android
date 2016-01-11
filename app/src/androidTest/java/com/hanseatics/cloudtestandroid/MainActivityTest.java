package com.hanseatics.cloudtestandroid;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;

@SmallTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkAddition() throws Exception {
        onView(withId(R.id.number1)).perform(typeText("12"), closeSoftKeyboard());
        onView(withId(R.id.operator)).perform(typeText("+"), closeSoftKeyboard());
        onView(withId(R.id.number2)).perform(typeText("23"), closeSoftKeyboard());
        onView(withId(R.id.solve)).perform(click());

        onView(withId(R.id.solution)).check(matches(withText("35.0")));
    }
}
