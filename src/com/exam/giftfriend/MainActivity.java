package com.exam.giftfriend;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.exam.giftfriend.fragments.EventFragment;
import com.exam.giftfriend.fragments.EventsListFragment;

public class MainActivity extends FragmentActivity
        implements EventsListFragment.OnEventSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.mainpage_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            EventsListFragment eventsListFragment = new EventsListFragment();
            eventsListFragment.setArguments(getIntent().getExtras());

            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.add(R.id.mainpage_fragment_container, eventsListFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onEventSelected(int position) {
        EventFragment eventFragment = (EventFragment)
                getFragmentManager().findFragmentById(R.id.event);

        if (eventFragment != null) {
            eventFragment.updateEventView(position);
        } else {
            EventFragment newEventFragment = new EventFragment();
            Bundle args = new Bundle();
            args.putInt(EventFragment.ARG_POSITION, position);
            newEventFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            //TODO fix this buggy behaviour - switched to getFragmentManager in order to work
            transaction.replace(R.id.mainpage_fragment_container, newEventFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.home_button) {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
        }
        if (id == R.id.friends_button) {
            Intent mainActivityIntent = new Intent(this, FriendsActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            // Default action on back pressed
            super.onBackPressed();
        }
    }
}
