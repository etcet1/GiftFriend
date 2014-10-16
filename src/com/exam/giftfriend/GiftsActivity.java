package com.exam.giftfriend;

import java.util.Iterator;
import java.util.List;

import com.exam.giftfriend.fragments.EventsListFragment;
import com.exam.giftfriend.sqlite.SQLiteRepo;
import com.exam.giftfriend.sqlite.models.Gift;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GiftsActivity  extends Activity implements OnClickListener{
	
	Button btnShowMine;
	Button btnShowAll;
	Button btnAdd;
		
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);
        
        btnShowMine = (Button)findViewById(R.id.btn_my_gifts);
        btnShowAll = (Button)findViewById(R.id.btn_public_gifts);
        btnAdd = (Button)findViewById(R.id.btn_add_gift);
        btnAdd.setOnClickListener(this);
        btnShowMine.setOnClickListener(this);
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
        	//Toast.makeText(this, "Clicked!" + id, Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.home_button) {
        	///Toast.makeText(this, "Clicked!" + id, Toast.LENGTH_SHORT).show();
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
        } else if (id == R.id.friends_button) {
        	//Toast.makeText(this, "Clicked!" + id, Toast.LENGTH_SHORT).show();
            Intent mainActivityIntent = new Intent(this, FriendsActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
        } else if (id == R.id.gifts_button) {
        	Intent mainActivityIntent = new Intent(this, GiftsActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
		}
        return super.onOptionsItemSelected(item);
    }

    /*public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            // Default action on back pressed
            super.onBackPressed();
        }
    }*/
    
    public void onClick(View v) {
		//btn_my_gifts -> show all gifts from SQLite
		//btn_public_gifts -> show all gifts from public base
		//btn_add_gift -> add gift in SQLite
    	int id = v.getId();
    	if (id == R.id.btn_add_gift) {
			AddGiftDialogFragment dialogFragment = new AddGiftDialogFragment();
			dialogFragment.show(getFragmentManager(), "giftFragment");
		} else if(id == R.id.btn_my_gifts){
			try {
				//Toast.makeText(this, "My gifts clicked", Toast.LENGTH_SHORT).show();
				Intent giftActivityIntent = new Intent(this, GiftsListActivity.class);
	            startActivityForResult(giftActivityIntent, 0);
			} catch (Exception e) {
				Log.e("ErrorGiftsList", e.getMessage(), e);
				e.printStackTrace();
			}
			
			
		}
	}
}
