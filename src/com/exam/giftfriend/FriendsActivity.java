package com.exam.giftfriend;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.exam.giftfriend.sqlite.SQLiteRepo;
import com.exam.giftfriend.sqlite.models.Friend;


public class FriendsActivity extends Activity implements OnClickListener{
	private SQLiteRepo friendsEntry;
	private TextView contactsView;
	private Button importBtn;
	private Button addBtn;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        
        friendsEntry = new SQLiteRepo(this);
        
        contactsView = (TextView)this.findViewById(R.id.view_contacts);
        importBtn = (Button)this.findViewById(R.id.btn_import_contacts);
        addBtn = (Button)this.findViewById(R.id.btn_add_new);
        
        contactsView.append(getAllFriendsToString(friendsEntry));
        
        importBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }

    @Override
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
        	//Toast.makeText(this, "Clicked!" + id, Toast.LENGTH_SHORT).show();
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
        } else if (id == R.id.friends_button) {
        	//Toast.makeText(this, "Clicked!" + id, Toast.LENGTH_SHORT).show();
            Intent mainActivityIntent = new Intent(this, FriendsActivity.class);
            startActivityForResult(mainActivityIntent, 0);
            return true;
        }else if (id == R.id.gifts_button) {
        	Intent mainActivityIntent = new Intent(this, GiftsActivity.class);
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

    private void recordContactsInDatabase(SQLiteRepo entry) {
    	Cursor cursor = this.getContacts();

        if (cursor.moveToFirst()) {
    	    do {
				String name = cursor.getString(cursor
			      .getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
				Friend newFriend = new Friend(name);
				
				if (entry.getFriendByName(newFriend.getName()) == null) {
					entry.createFriend(newFriend);
				}
        	} while (cursor.moveToNext());
		}
    }

	private Cursor getContacts() {
	    // Run query
	    Uri uri = ContactsContract.Contacts.CONTENT_URI;
	    String[] projection = new String[] { ContactsContract.Contacts._ID,
	        ContactsContract.Contacts.DISPLAY_NAME };
	    String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
	        + ("1") + "'";
	    String[] selectionArgs = null;
	    String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
	        + " COLLATE LOCALIZED ASC";

	    
	    return managedQuery(uri, projection, selection, selectionArgs,
	        sortOrder);
    }

	private String getAllFriendsToString(SQLiteRepo entry) {
		List<Friend> friendsList = entry.getAllFriends();
		StringBuilder output = new StringBuilder();
        
        if (friendsList.size() > 0) {
            for (Friend friend : friendsList) {
            	output.append(friend.getName());
            	output.append("\n");
    		}
		}
        
        return output.toString();
	}

	public void onClick(View v) {
		if(v.getId() == R.id.btn_import_contacts) {
			recordContactsInDatabase(friendsEntry);
			contactsView.append(getAllFriendsToString(friendsEntry));
		}
		else if(v.getId() == R.id.btn_add_new) {
			//TODO implementation with dialog fragment
			Friend newFriend = new Friend("Stamat");
			friendsEntry.createFriend(newFriend);
			contactsView.append(newFriend.getName());
		}
	}
}
