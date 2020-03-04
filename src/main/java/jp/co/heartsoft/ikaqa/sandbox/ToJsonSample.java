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
        //単体Json化
        User user1 = new User("01", null);       //fullnameにnullをセット
        User user2 = new User("02", "Jeff");

        // Json形式になったもの
        System.out.println("ユーザー1: " + gson.toJson(user1));
        System.out.println("ユーザー2: " + gson.toJson(user2));

        //List後Json化
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        // Json形式になったもの：List
        System.out.println("ユーザーリスト: " + gson.toJson(userList));

        //フィールドにListがあるものにデータを追加後Json化
        Question newQuestion = new Question(userList.get(0), "questionTitle", "questionContent");
        Comment comment = new Comment("comment_author", "comment_comment");
        newQuestion.comments.add(comment);
        newQuestion.comments.add(comment);

        // JavaオブジェクトからJSONへの変換：フィールドにListを含むオブジェクト
        System.out.println("コメント付き投稿: " + gson.toJson(newQuestion));
    }
}
