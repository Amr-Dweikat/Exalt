package Testing;

import Classes.Help;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestPatchRESTApi {

    private final static String POST_URL = "https://jsonplaceholder.typicode.com/posts/";
    private final static String POST_FIELDS[] = {"id" , "title" };
    private Help help = new Help();


    @Test
    public void testPatchMethodForPostGotByID(){
        String newPostDataFields []= {"5000","first step for automation testing"};
        JSONObject data = help.createPostData(POST_FIELDS , newPostDataFields);
        help.validatePatchMethodForPostGotByID(POST_URL , 10 , data);
    }

    @Test
    public void testPatchMethodForPostGotByWrongID(){
        String newPostDataFields []= {"5000","testing"};
        JSONObject data = help.createPostData(POST_FIELDS , newPostDataFields);
        help.validatePatchMethodForPostGotByWrongID(POST_URL , 400 , data);
    }

    @Test
    public void testPatchMethodForWrongDataPostGotByID(){
        String newPostDataFields []= {"amr","learning"};
        JSONObject data = help.createPostData(POST_FIELDS , newPostDataFields);
        help.validatePatchMethodForWrongDataPostGotByID(POST_URL , 5, data);
    }
}
