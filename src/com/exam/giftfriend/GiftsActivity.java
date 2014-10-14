package com.exam.giftfriend;

import com.exam.giftfriend.fragments.EventsListFragment;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GiftsActivity  extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	Toast.makeText(this, "Clicked!" + id, Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.home_button) {
        	Toast.makeText(this, "Clicked!" + id, Toast.LENGTH_SHORT).show();
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
        } else if (id == R.id.friends_button) {
        	Toast.makeText(this, "Clicked!" + id, Toast.LENGTH_SHORT).show();
            Intent mainActivityIntent = new Intent(this, FriendsActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
        } else if (id == R.id.gifts_button) {
        	//Intent mainActivityIntent = new Intent(this, GiftsActivity.class);
            //startActivityForResult(mainActivityIntent, 0);
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
