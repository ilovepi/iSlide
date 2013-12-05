package edu.csun.group2.islide.engine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SolutionHelper extends SQLiteOpenHelper {

  public static final String TABLE_SOLUTIONS = "solutions";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_SOLUTION_ID = "solution_id";
  public static final String COLUMN_MOVE_NUMBER = "move_number";
  public static final String COLUMN_MOVE_INDEX = "move_index";
  public static final String COLUMN_BOARD = "board";
  
  private static final String DATABASE_NAME = "solutions.db";
  private static final int DATABASE_VERSION = 1;

  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_SOLUTIONS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_SOLUTION_ID
      + " long, " + COLUMN_MOVE_NUMBER +" integer autoincrement, "+ COLUMN_MOVE_INDEX+" integer, "+
      COLUMN_BOARD +" text );";

  public SolutionHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(SolutionHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOLUTIONS);
    onCreate(db);
  }

} 


