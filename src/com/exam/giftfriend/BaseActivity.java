package com.exam.giftfriend;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

abstract class BaseActivity extends Activity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.home_button) {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            return true;
        } else if (id == R.id.friends_button) {
            Intent mainActivityIntent = new Intent(this, FriendsActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            return true;
        } else if (id == R.id.gifts_button) {
            Intent mainActivityIntent = new Intent(this, GiftsActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        } else {
            // Default action on back pressed
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }
}
