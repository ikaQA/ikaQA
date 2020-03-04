package jp.co.heartsoft.ikaqa.sandbox;

import com.google.gson.Gson;

import java.util.List;

public class FromJsonSample {

    /*
     *  JSONからJavaオブジェクトへの変換
     */
    public static void main(String[] args) {
        Gson gson = new Gson();

        // JSONからStringへの変換
        String str = gson.fromJson("\"hello\"", String.class);
        System.out.println("String: " + str);

        // JSONからJavaオブジェクトへの変換
        User user = gson.fromJson("{\"userId\":\"01\",\"fullname\":\"Bob\"}", User.class);
        System.out.println("User: " + user.userId + " / " + user.fullname);

        // JSONから配列への変換
        int[] array = gson.fromJson("[1, 2, 3]", int[].class);
        System.out.println("int[]: " + array[0] + ",　" + array[1] + ",　" + array[2]);

        // JSONからListへの変換
        List list = gson.fromJson("[\"hello\", \"hellohello\",\"hellohellohello\"]", List.class);
        System.out.println("List: " + list.get(0) + ",　" + list.get(1) + ",　" + list.get(2));

        // JSONからフィールドにListを含むJavaオブジェクトへの変換
        String jsonStr = "{\"title\":\"投稿タイトル\",\"content\":\"本文本文本文\","
                + "\"author\":{\"userId\":\"01\",\"fullname\":\"Bob\"},"
                + "\"comments\":[{\"author\":\"Tom\",\"content\":\"コメント本文\"}]"
                + "}";
        Post post = gson.fromJson(jsonStr, Post.class);
        System.out.println("Post: タイトル=" + post.title
                + ", 著者=" + post.author.fullname
                + ", コメント件数=" + post.comments.size());
    }
}