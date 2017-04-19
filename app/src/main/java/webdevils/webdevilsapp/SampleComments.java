package webdevils.webdevilsapp;

import webdevils.webdevilsapp.Comment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caseyfroke on 4/18/17.
 */

public class SampleComments {
    public static List<Comment> commentList;
    public static Map<String, Comment> commentMap;

    static {
        commentList = new ArrayList<>();
        commentMap = new HashMap<>();

        addComment(new Comment(null, "bob: I really like what you have there!!"));
        addComment(new Comment(null, "Casey: I like the idea you have, nice thinking!!"));
        addComment(new Comment(null, "KyleCarey: I was thinking the same thing!!"));
        addComment(new Comment(null, "Kevin: I really like the direction you went!!"));
        addComment(new Comment(null, "Wing: Glad you had that idea, hope they use it!!"));
        addComment(new Comment(null, "jane: I like what you have going there!!"));
        addComment(new Comment(null, "dan: I like that you are thinking of these things!!"));
        addComment(new Comment(null, "Martin: I really like what you have there!!"));
    }

    private static void addComment(Comment comment) {
        commentList.add(comment);
        commentMap.put(comment.getID(), comment);
    }
}
