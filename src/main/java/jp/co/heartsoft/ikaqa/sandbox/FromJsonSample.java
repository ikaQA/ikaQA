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
        String jsonStr = "{\"title\":\"質問があります。\",\"content\":\"パンが食べたい\","
                + "\"author\":{\"userId\":\"01\",\"fullname\":\"Bob\"},"
                + "\"comments\":[{\"author\":\"Tom\",\"content\":\"バナナが食べたい。\"}]"
                + "}";
        Question question = gson.fromJson(jsonStr, Question.class);
        System.out.println("Question: タイトル=" + question.title
                + ", 著者=" + question.author.fullname
                + ", コメント件数=" + question.comments.size());
    }
}