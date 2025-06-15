package com.dicoding.latihan_praktikum_10;



import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import com.dicoding.latihan_praktikum_10.presentation.ui.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Test
    public void testTitleDisplayed() {
        ActivityScenario.launch(MainActivity.class);
        onView(withId(R.id.app_title))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}