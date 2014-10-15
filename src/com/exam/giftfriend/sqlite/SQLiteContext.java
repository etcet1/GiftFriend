package com.exam.giftfriend.sqlite;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.exam.giftfriend.sqlite.models.Friend;
import com.exam.giftfriend.sqlite.models.Gift;

public class SQLiteContext extends SQLiteOpenHelper {

    // Logcat tag
    protected static final String LOG = "SQLiteContext";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GiftFriendDB";

    /*
    // Table Names
    private static final String TABLE_FRIENDS = "friends";
    private static final String TABLE_GIFTS = "gifts";

    // Common column names
    private static final String KEY_ID = "_id";
    private static final String KEY_TAG_NAME = "name";
    // friends table Create Statements
    private static final String CREATE_TABLE_FIRENDS = "CREATE TABLE "
            + TABLE_FRIENDS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME
            + " TEXT,)";
    private static final String KEY_CREATED_AT = "created_at";
    
    // GIFTS Table - column names
    private static final String KEY_STATUS = "status";
    private static final String KEY_TAG_LOCATION = "location";
    // GIFTS table create statement
    private static final String CREATE_TABLE_GIFTS = "CREATE TABLE " + TABLE_GIFTS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
            + KEY_STATUS + " INTEGER," + KEY_TAG_LOCATION + " TEXT," + KEY_CREATED_AT + " DATETIME" + ")";
	*/
    
    public SQLiteContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
    	FriendsTable.onCreate(db);
    	GiftsTable.onCreate(db);
    	CategoryTable.onCreate(db);
    	FriendGiftTable.onCreate(db);
        //db.execSQL(CREATE_TABLE_FIRENDS);
        //db.execSQL(CREATE_TABLE_GIFTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
    	FriendsTable.onUpgrade(db, oldVersion, newVersion);
    	GiftsTable.onUpgrade(db, oldVersion, newVersion);
    	CategoryTable.onUpgrade(db, oldVersion, newVersion);
    	FriendGiftTable.onUpgrade(db, oldVersion, newVersion);
        //db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_FIRENDS);
        //db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_GIFTS);

        // recreate tables
        onCreate(db);
    }
}
