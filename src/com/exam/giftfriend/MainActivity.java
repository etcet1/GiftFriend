package com.exam.giftfriend;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.exam.giftfriend.fragments.EventFragment;
import com.exam.giftfriend.fragments.EventsListFragment;

public class MainActivity extends BaseActivity
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
            
            System.out.println(eventsListFragment.toString());
            
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            transaction.add(R.id.mainpage_fragment_container, eventsListFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
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
}
