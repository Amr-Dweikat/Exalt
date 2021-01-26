package Testing;

import Classes.Help;
import org.testng.annotations.Test;

public class TestDeleteRESTApi {

    private final static String POST_URL = "https://jsonplaceholder.typicode.com/posts/";
    private Help help = new Help();

    @Test
    public void testDeleteMethodForPostGotByID(){
        help.validateDeleteMethodForPostGotByID(POST_URL , 15);
    }

    @Test
    public void testDeleteMethodForPostGotByWrongID(){
        help.validateDeleteMethodForPostGotByWrongID(POST_URL , 250);
    }


}
