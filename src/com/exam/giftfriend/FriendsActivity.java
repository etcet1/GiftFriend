package com.exam.giftfriend;

import java.util.List;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.exam.giftfriend.sqlite.SQLiteRepo;
import com.exam.giftfriend.sqlite.models.Friend;


public class FriendsActivity extends BaseActivity implements OnClickListener{
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
	    String[] projection = new String[] { 
	    	ContactsContract.Contacts._ID,
	        ContactsContract.Contacts.DISPLAY_NAME 
	        };
	    String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
	        + ("1") + "'";
	    String[] selectionArgs = null;
	    String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
	        + " COLLATE LOCALIZED ASC";

	    
//	    return managedQuery(uri, projection, selection, selectionArgs,
//	        sortOrder);
	    return getApplicationContext().getContentResolver().query(uri, projection, null, selectionArgs,
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
