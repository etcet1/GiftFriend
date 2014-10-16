package com.exam.giftfriend.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class FriendGiftTable {
	public static final String TABLE_FRIEND_GIFT = "FriendGift";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_FRIEND_ID = "friend_id";
	public static final String COLUMN_GIFT_ID = "gift_id";

	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table " + TABLE_FRIEND_GIFT
			+ "(" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_FRIEND_ID + " integer not null, "
			+ COLUMN_GIFT_ID + " integer not null);";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(FriendsTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND_GIFT);
		onCreate(database);
	}
}
