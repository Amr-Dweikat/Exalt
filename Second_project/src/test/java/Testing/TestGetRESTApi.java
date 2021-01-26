package Testing;

import Classes.Help;
import org.testng.annotations.Test;



public class TestGetRESTApi {

            private final static String URL = "https://jsonplaceholder.typicode.com/";
            private final static String POSTS_URL = URL + "posts/";
            private final static String USERS_URL = URL + "users/";
            private Help help = new Help();


            @Test
            public void testGetForPosts(){
                help.validateTotalPosts(POSTS_URL);
            }

            @Test
            public void testGetForUsers(){
                help.validateTotalUsers(USERS_URL);
            }


            @Test
            public void testGetForPostGotByID(){
                help.validatePostInformation(POSTS_URL,1);
            }

            @Test
            public void testGetForUserGotByID(){
                help.validateUserInformation(USERS_URL,1);
            }


            @Test
            public void testGetCommentsFieldForPostByID(){
                help.validateTotalCommentsInPost(POSTS_URL,1 , "/comments");
            }

            @Test
            public void testGetAlbumsFieldForUserGotByID(){
                help.validateTotalAlbumsForUser(USERS_URL,1 , "/albums");
            }


            @Test
            public  void testGetForPostsGotByIDQueryParameters(){
                help.validatePostInformation(POSTS_URL.replace("posts/","posts?id="),2);
            }

            @Test
            public  void testGetForUsersGotByIDQueryParameters(){
                help.validateUserInformation(USERS_URL.replace("users/","users?id="),2);
            }


            @Test
            public void testGetForPostGotByWrongID(){
                help.validateWrongPostInformation(POSTS_URL ,200);
            }

            @Test
            public void testGetForUserGotByWrongID(){
                help.validateWrongUserInformation(USERS_URL ,20);
            }

}
