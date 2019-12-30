package com.example.todomvp;

import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.Root;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.todomvp.ui.main.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TodayFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkSaveFragment() {
        Espresso.onView(ViewMatchers.withId(R.id.fab))
        .perform(click());

        onView(withId(R.id.save_button));
    }

    @Test
    public void checkEmptyTextAndDateFields() {
        Espresso.onView(ViewMatchers.withId(R.id.fab))
                .perform(click());

        onView(withId(R.id.save_button))
        .perform(click())
        .inRoot(new ToastMatcher()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void checkEmptyDateField() {
        Espresso.onView(ViewMatchers.withId(R.id.fab))
                .perform(click());

        onView(withId(R.id.text_edit_text)).perform(typeText("One"));
        onView(withId(R.id.save_button))
                .perform(click())
                .inRoot(new ToastMatcher3()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void checkEmptyTextField() {
        Espresso.onView(ViewMatchers.withId(R.id.fab))
                .perform(click());

        setDate(2019, 12, 27);

        onView(withId(R.id.save_button))
                .perform(click())
                .inRoot(new ToastMatcher2()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    public static void setDate( int year, int monthOfYear, int dayOfMonth) {
        onView(withId(R.id.date_text_view)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear, dayOfMonth));
        onView(withId(android.R.id.button1)).perform(click());
    }

    public static Matcher<View> matchesDate(final String date) {         //Custom Date matcher
        return new BoundedMatcher<View, DatePicker>(DatePicker.class) {


            @Override
            public void describeTo(Description description) {
                description.appendText("matches date:");
            }

            @Override
            protected boolean matchesSafely(DatePicker item) {
                return date == String.valueOf(item.getMaxDate());
            }
        };
    }
}

class ToastMatcher extends TypeSafeMatcher<Root> {

    @Override
    public boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                // windowToken == appToken means this window isn't contained by any other windows.
                // if it was a window for an activity, it would have TYPE_BASE_APPLICATION.
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(org.hamcrest.Description description) {
        description.appendText("Text and Date Fields are empty!");
    }
}

class ToastMatcher2 extends TypeSafeMatcher<Root> {

    @Override
    protected boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                // windowToken == appToken means this window isn't contained by any other windows.
                // if it was a window for an activity, it would have TYPE_BASE_APPLICATION.
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Text Field is empty!");
    }
}

class ToastMatcher3 extends TypeSafeMatcher<Root> {


    @Override
    protected boolean matchesSafely(Root root) {
        int type = root.getWindowLayoutParams().get().type;
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            IBinder windowToken = root.getDecorView().getWindowToken();
            IBinder appToken = root.getDecorView().getApplicationWindowToken();
            if (windowToken == appToken) {
                // windowToken == appToken means this window isn't contained by any other windows.
                // if it was a window for an activity, it would have TYPE_BASE_APPLICATION.
                return true;
            }
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Date Field is empty!");
    }
}
