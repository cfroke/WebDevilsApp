/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * This class is used to generate the data or the database and to allow opening and closing of the connection
 */

public class CommentDataSource {

    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public CommentDataSource(Context context) {
        this.mContext = context;
        mDbHelper = new CommentsDB(mContext);
        mDatabase = mDbHelper.getReadableDatabase();
    }

    public void open() {
        mDatabase = mDbHelper.getReadableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public Comment createComment(Comment comment) {
        ContentValues values = comment.toValues();
        mDatabase.insert(CommentTable.TABLE_COMMENTS, null, values);
        return comment;
    }

    public long getCommentCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, CommentTable.TABLE_COMMENTS);
    }

    public void seedDatabase(List<Comment> commentList) {
        long numComments = getCommentCount();
        if (numComments == 0) {
            for (Comment comment : commentList) {
                try {
                    createComment(comment);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Comment> getAllComments() {
        List<Comment> commentAll = new ArrayList<>();
        Cursor cursor = mDatabase.query(CommentTable.TABLE_COMMENTS, CommentTable.ALL_COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Comment comment = new Comment();
            comment.setId(cursor.getString(cursor.getColumnIndex(CommentTable.COLUMN_ID)));
            comment.setComment(cursor.getString(cursor.getColumnIndex(CommentTable.COLUMN_COMMENT)));
        }
        return commentAll;
    }

}
