package jp.co.heartsoft.ikaqa.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Question {
    public String title;
    public String content;
    public User author;
    public List<Comment> comments;

    public Question(User author, String title, String content) {
        this.comments = new ArrayList<Comment>();
        this.author = author;
        this.title = title;
        this.content = content;
    }
}