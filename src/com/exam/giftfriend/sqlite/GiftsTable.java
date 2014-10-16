package com.exam.giftfriend.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GiftsTable {
	public static final String TABLE_GIFTS = "gifts";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_SHOP = "shop";
	public static final String COLUMN_CATEGORY_ID = "category_id";

	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table " + TABLE_GIFTS
			+ "(" + COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_NAME + " text not null, " + COLUMN_SHOP + " text, "
			+ COLUMN_CATEGORY_ID + " text);";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(FriendsTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_GIFTS);
		onCreate(database);
	}
}
