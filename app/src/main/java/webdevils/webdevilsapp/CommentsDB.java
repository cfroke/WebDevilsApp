/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This Class is used to  create the database and check to see if it needs updating
 */

public class CommentsDB extends SQLiteOpenHelper{


    private static final String DATABASE_NAME = "comments.db";
    private static final int DATABASE_VERSION = 1;


    public CommentsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CommentTable.DATABASE_CREATE);
        System.out.println("Database created..........");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(CommentTable.DATABASE_DELETE);
        onCreate(database);
    }

}
