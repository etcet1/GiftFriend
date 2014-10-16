package com.exam.giftfriend.sqlite;

import static com.exam.giftfriend.sqlite.SQLiteContext.LOG;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.exam.giftfriend.sqlite.models.Friend;
import com.exam.giftfriend.sqlite.models.Gift;

public class SQLiteRepo extends SQLiteContext{

	public SQLiteRepo(Context context) {
		super(context);
	}

	/**
     * Creating a gift
     */
    public long createGift(Gift gift) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(GiftsTable.COLUMN_NAME, gift.getName());
        values.put(GiftsTable.COLUMN_SHOP, gift.getLocation());
        //values.put(GiftsTable.COLUMN_CATEGORY_ID, gift.getCategoryId());
        
        // values.put(KEY_CREATED_AT, getDateTime());

        long gift_id = db.insert(GiftsTable.TABLE_GIFTS, null, values);

        return gift_id;
    }

    /**
     * get single gift
     */
    public Gift getGift(int gift_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + GiftsTable.TABLE_GIFTS + " WHERE "
                + GiftsTable.COLUMN_ID + " = " + gift_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null) {
            c.moveToFirst();
        }

        Gift gift = new Gift();
        gift.setId(c.getInt(c.getColumnIndex(GiftsTable.COLUMN_ID)));
        gift.setName((c.getString(c.getColumnIndex(GiftsTable.COLUMN_NAME))));
        // gift.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return gift;
    }

    /**
     * getting all gifts
     */
    public List<Gift> getAllGifts() {
        List<Gift> gifts = new ArrayList<Gift>();
        String selectQuery = "SELECT  * FROM " + GiftsTable.TABLE_GIFTS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Gift gift = new Gift();
                gift.setId(c.getInt(c.getColumnIndex(GiftsTable.COLUMN_ID)));
                gift.setName((c.getString(c.getColumnIndex(GiftsTable.COLUMN_NAME))));
                //gift.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

                gifts.add(gift);
            } while (c.moveToNext());
        }

        return gifts;
    }

    /**
     * get todo count
     */
    public int getGiftsCount() {
        String countQuery = "SELECT  * FROM " + GiftsTable.TABLE_GIFTS;
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
        values.put(GiftsTable.COLUMN_ID, gift.getId());
        //values.put(KEY_STATUS, gift.getStatus());

        return db.update(GiftsTable.TABLE_GIFTS, values, GiftsTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(gift.getId())});
    }

    /**
     * Creating friend
     */
    public long createFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FriendsTable.COLUMN_NAME, friend.getName());

        // insert row
        long friend_id = db.insert(FriendsTable.TABLE_FRIENDS, null, values);

        return friend_id;
    }

    /**
     * getting all friends
     */
    public List<Friend> getAllTags() {
        List<Friend> friends = new ArrayList<Friend>();
        String selectQuery = "SELECT  * FROM " + FriendsTable.TABLE_FRIENDS;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Friend friend = new Friend();
                friend.setId(c.getInt((c.getColumnIndex(FriendsTable.COLUMN_ID))));
                friend.setName(c.getString(c.getColumnIndex(FriendsTable.COLUMN_NAME)));

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
