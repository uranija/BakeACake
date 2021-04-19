package com.example.android.bakingapp;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.android.bakingapp.ui.RecipeDetailActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

public class RecipeDetailActivityTabletTest {

    @Rule
    public ActivityTestRule<RecipeDetailActivity> mActivityTestRule = new ActivityTestRule<>(RecipeDetailActivity.class);

    // This test only works with tablets
    @Test
    public void recipeDetailActivityTabletTest() {
        onView(ViewMatchers.withId(R.id.item_detail_container)).check(matches(isDisplayed()));
    }





}
