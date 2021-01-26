package Testing;

import Classes.Help;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestPutRESTApi {

    private final static String POST_URL = "https://jsonplaceholder.typicode.com/posts/";
    private final static String POST_FIELDS[] = {"userId" , "id" , "title" , "body"};
    private Help help = new Help();


    @Test
    public void testPutMethodForPostGotByID(){
        String newPostDataFields []= {"1000","1000","automation testing","learning"};
        JSONObject data = help.createPostData(POST_FIELDS , newPostDataFields);
        help.validatePutMethodForPostGotByID(POST_URL , 2 , data);
    }

    @Test
    public void testPutMethodForPostGotByWrongID(){
        String newPostDataFields []= {"1001","2000","automation testing","learning"};
        JSONObject data = help.createPostData(POST_FIELDS , newPostDataFields);
        help.validatePutMethodForPostGotByWrongID(POST_URL , 200 , data);
    }

    @Test
    public void testPutMethodForWrongDataPostGotByID(){
        String newPostDataFields []= {"amr","dweikat","automation testing","learning"};
        JSONObject data = help.createPostData(POST_FIELDS , newPostDataFields);
        help.validatePutMethodForWrongDataPostGotByID(POST_URL , 1 , data);
    }

}
