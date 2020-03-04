package jp.co.heartsoft.ikaqa.sandbox;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ToJsonSample {
    /*
     *  JavaオブジェクトからJSONへの変換
     */
    public static void main(String[] args) {
        Gson gson = new Gson();

        User user1 = new User("01", null);       //fullnameにnullをセット
        User user2 = new User("02", "Jeff");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        // JavaオブジェクトからJSONへの変換
        System.out.println("ユーザー: " + gson.toJson(user1));

        // JavaオブジェクトからJSONへの変換：List
        System.out.println("ユーザーリスト: " + gson.toJson(userList));

        Post newPost = new Post(userList.get(0), "postTitle", "postContent");
        Comment comment = new Comment("comment_author", "comment_comment");
        newPost.comments.add(comment);
        newPost.comments.add(comment);

        // JavaオブジェクトからJSONへの変換：フィールドにListを含むオブジェクト
        System.out.println("コメント付き投稿: " + gson.toJson(newPost));
    }
}
