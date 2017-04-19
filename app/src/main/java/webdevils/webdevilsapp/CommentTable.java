package webdevils.webdevilsapp;

/**
 *    SER 401 / 402 -- Senior Project -- WebDevils -- Project 11
 */

/**
 * This class is used to designate the table layout for the SQLite DB
 */

public class CommentTable {

    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COMMENT = "comment";

    public static final String[] ALL_COLUMNS =
            {COLUMN_ID, COLUMN_COMMENT};

    public static final String DATABASE_CREATE =
            "CREATE TABLE " + TABLE_COMMENTS + "( " +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_COMMENT + " TEXT " + ");";

    public static final String DATABASE_DELETE =
            "DROP TABLE " + TABLE_COMMENTS;
}
