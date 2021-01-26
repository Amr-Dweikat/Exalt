package Testing;

import Classes.Help;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestPostRESTApi {

    private final static String POST_URL = "https://jsonplaceholder.typicode.com/posts";
    private final static String POST_FIELDS[] = {"userId" , "id" , "title" , "body"};
    private Help help = new Help();


    @Test
    public void testPostMethodForPosts(){
        String postDataFields []= {"1000","1000","automation testing","learning"};
        JSONObject data = help.createPostData(POST_FIELDS , postDataFields);
        help.validatePostMethodForPosts(POST_URL , data);
    }

    @Test
    public void testPostMethodForWrongDataPosts(){
        String postDataFields []= {"amr","dweikat","automation testing","learning"};
        JSONObject data = help.createPostData(POST_FIELDS , postDataFields);
        help.validatePostMethodForWrongDataPosts(POST_URL , data);
    }
}
