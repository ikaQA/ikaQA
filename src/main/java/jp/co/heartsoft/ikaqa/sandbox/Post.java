package jp.co.heartsoft.ikaqa.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Post {
    public String title;
    public String content;
    public User author;
    public List<Comment> comments;

    public Post(User author, String title, String content) {
        this.comments = new ArrayList<Comment>();
        this.author = author;
        this.title = title;
        this.content = content;
    }
}