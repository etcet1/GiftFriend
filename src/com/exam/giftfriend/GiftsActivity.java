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

public class GiftsActivity  extends BaseActivity implements OnClickListener{
	
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
