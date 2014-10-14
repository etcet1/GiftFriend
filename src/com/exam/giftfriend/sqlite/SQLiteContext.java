package com.exam.giftfriend.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.exam.giftfriend.sqlite.models.Friend;
import com.exam.giftfriend.sqlite.models.Gift;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SQLiteContext extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "SQLiteContext";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GiftFriendDB";

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

    public SQLiteContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_FIRENDS);
        db.execSQL(CREATE_TABLE_GIFTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_FIRENDS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_GIFTS);

        // recreate tables
        onCreate(db);
    }

    /**
     * Creating a gift
     */
    public long createGift(Gift gift) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAG_NAME, gift.getName());
        values.put(KEY_STATUS, gift.getStatus());
        values.put(KEY_CREATED_AT, getDateTime());

        long gift_id = db.insert(TABLE_GIFTS, null, values);

        return gift_id;
    }

    /**
     * get single gift
     */
    public Gift getGift(long gift_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_GIFTS + " WHERE "
                + KEY_ID + " = " + gift_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
        }

        Gift gift = new Gift();
        gift.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        gift.setName((c.getString(c.getColumnIndex(KEY_TAG_NAME))));
        gift.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return gift;
    }

    /**
     * getting all gifts
     */
    public List<Gift> getAllGifts() {
        List<Gift> gifts = new ArrayList<Gift>();
        String selectQuery = "SELECT  * FROM " + TABLE_GIFTS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Gift gift = new Gift();
                gift.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                gift.setName((c.getString(c.getColumnIndex(KEY_TAG_NAME))));
                gift.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                gifts.add(gift);
            } while (c.moveToNext());
        }

        return gifts;
    }

    /**
     * get todo count
     */
    public int getGiftsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_GIFTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    /**
     * Update a gift
     */
    public int updateGift(Gift gift) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, gift.getId());
        values.put(KEY_STATUS, gift.getStatus());

        return db.update(TABLE_GIFTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(gift.getId())});
    }

    /**
     * Creating friend
     */
    public long createFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TAG_NAME, friend.getName());

        // insert row
        long friend_id = db.insert(TABLE_FRIENDS, null, values);

        return friend_id;
    }

    /**
     * getting all friends
     */
    public List<Friend> getAllTags() {
        List<Friend> friends = new ArrayList<Friend>();
        String selectQuery = "SELECT  * FROM " + TABLE_FRIENDS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Friend friend = new Friend();
                friend.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                friend.setName(c.getString(c.getColumnIndex(KEY_TAG_NAME)));

                friends.add(friend);
            } while (c.moveToNext());
        }
        return friends;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
