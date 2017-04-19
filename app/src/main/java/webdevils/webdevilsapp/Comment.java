/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */
package webdevils.webdevilsapp;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import common.User;

/**
 *  This class is used to parse the Database so that a user can add, delete, set and get data
 */

public class Comment implements Parcelable{
    private String id;
    private String comment;
    private final User currentUser = LoginActivity.currentUser;

    public Comment() {

    }

    /**
     * @param id
     * @param comment
     */
    public Comment(String id, String comment) {
        if (id == null) {
            id = currentUser.toString();
        }

        this.id = id;
        this.comment = comment;
    }

    /**
     * @return
     */
    public String getID() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return
     */
    public ContentValues toValues() {
        ContentValues values = new ContentValues(1);

        values.put(CommentTable.COLUMN_ID, id);
        values.put(CommentTable.COLUMN_COMMENT, comment);
        return values;
    }

    @Override
    public String toString() {
        return comment;
    }

    /**
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel (Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.comment);
    }

    /**
     * @param in
     */
    protected Comment(Parcel in) {
        this.id = in.readString();
        this.comment = in.readString();
    }

    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
