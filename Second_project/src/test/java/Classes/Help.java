package Classes;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;



public class Help {


       public void validateTotalPosts (String url){

           Response response = RestAssured.get(url);
           Assert.assertEquals(200,response.getStatusCode(),"There is an error in get rest api method");
           List<String> allPosts = response.jsonPath().get("id");
           Assert.assertEquals(100, allPosts.size(),"number of posts not correct");
       }


       public void validateTotalUsers (String url){

            Response response = RestAssured.get(url);
            Assert.assertEquals(200,response.getStatusCode(),"There is an error in get rest api method");
            List<String> allUsers = response.jsonPath().get("email");
            Assert.assertEquals(10, allUsers.size(),"number of users not correct");
       }


       public void validatePostInformation(String url , int id){

            Response response = RestAssured.get(url+id);
            Assert.assertEquals(200, response.getStatusCode(), "There is an error in get rest api method");
            if(url.contains("?")) {
                Assert.assertEquals(1, new ArrayList<String>(response.jsonPath().get("id")).size(), "number of posts by using get by id query parameters must be one");
            }
            else {
                try {
                    JSONParser parser = new JSONParser();
                    JSONObject object = (JSONObject) parser.parse(response.body().asString());
                }
                catch (Exception exception){
                    System.out.println("number of posts by using get by id must be one");
                    throw new AssertionError();
                }
            }

            String fields [] = {"userId" , "id" , "title" , "body"};
            String errorMessage = new String("");
            for (String field : fields) {
                try {
                    Assert.assertNotNull(response.jsonPath().get(field).toString());
                }
                catch (AssertionError error) {
                    errorMessage += "\n post "+field+" by using get by ID empty";
                }
            }
            System.out.println(errorMessage);
       }


       public void validateUserInformation(String url , int id){

            Response response = RestAssured.get(url+id);
            Assert.assertEquals(200, response.getStatusCode(), "There is an error in get rest api method");
            if(url.contains("?")) {
                Assert.assertEquals(1, new ArrayList<String>(response.jsonPath().get("id")).size(), "number of users by using get by id query parameters must be one");
            }
            else {
                try {
                    JSONParser parser = new JSONParser();
                    JSONObject object = (JSONObject) parser.parse(response.body().asString());
                }
                catch (Exception exception){
                    System.out.println("number of users by using get by id must be one");
                    throw new AssertionError();
                }
               }
            String fields [] = {"id" , "name" , "username" , "email" , "phone" , "website"};
            String errorMessage = new String("");
            for (String field : fields) {
                try {
                    Assert.assertNotNull(response.jsonPath().get(field).toString());
                    }
                catch (AssertionError error) {
                    errorMessage += "\n user "+field+" by using get by ID empty";
                }
            }
            System.out.println(errorMessage);
       }


       public void validateTotalCommentsInPost(String url , int id , String field){

            Response response = RestAssured.get(url + id + field);
            Assert.assertEquals(200,response.getStatusCode(),"There is an error in get rest api method");
            List<String> allComments  =  response.jsonPath().get("body");
            Assert.assertEquals(5,allComments.size(),"number of comments not correct");
       }


       public void validateTotalAlbumsForUser(String url , int id , String field){

            Response response = RestAssured.get(url + id + field);
            Assert.assertEquals(200,response.getStatusCode(),"There is an error in get rest api method");
            List<String> allAlbums  =  response.jsonPath().get("id");
            Assert.assertEquals(10,allAlbums.size(),"number of albums not correct");
       }


       public void validateWrongPostInformation(String url , int id){

            Response response = RestAssured.get(url+id);
            Assert.assertEquals(404, response.getStatusCode(), "There is an error in get rest api method");
            Assert.assertEquals("{}",response.body().asString(),"no post has id like this "+id);
        }


       public void validateWrongUserInformation(String url , int id){

            Response response = RestAssured.get(url+id);
            Assert.assertEquals(404, response.getStatusCode(), "There is an error in get rest api method");
            Assert.assertEquals("{}",response.body().asString(),"no user has id like this "+id);
        }


       public JSONObject createPostData(String keys[] , String values[]){
           JSONObject object = new JSONObject();
           for (int i = 0; i < keys.length;i++){
               object.put(keys[i],values[i]);
           }
           return object;
       }


       public void validatePostMethodForPosts(String url , JSONObject data){
           RequestSpecification request = RestAssured.given();
           request.header("Content-Type","application/json");
           request.body(data.toJSONString());
           Response response = request.post(url);
           Assert.assertEquals(201,response.getStatusCode(),"There is an error in rest api post method");
       }


       public void validatePostMethodForWrongDataPosts(String url , JSONObject data){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            request.body(data.toJSONString());
            Response response = request.post(url);
            Assert.assertEquals(400,response.getStatusCode(),"There is an error in rest api post method");
       }


       public void validatePutMethodForPostGotByID(String url , int id , JSONObject data){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            request.body(data.toJSONString());
            Response response = request.put(url + id);
            Assert.assertEquals(200,response.getStatusCode(),"There is an error in rest api put method");
       }


       public void validatePutMethodForPostGotByWrongID(String url , int id , JSONObject data){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            request.body(data.toJSONString());
            Response response = request.put(url + id);
            Assert.assertEquals(404,response.getStatusCode(),"There is an error in rest api put method");
       }


       public void validatePutMethodForWrongDataPostGotByID(String url , int id , JSONObject data){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            request.body(data.toJSONString());
            Response response = request.put(url + id);
            Assert.assertEquals(400,response.getStatusCode(),"There is an error in rest api put method");
       }


       public void validatePatchMethodForPostGotByID(String url , int id , JSONObject data){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            request.body(data.toJSONString());
            Response response = request.patch(url + id);
            Assert.assertEquals(200,response.getStatusCode(),"There is an error in rest api patch method");
       }


       public void validatePatchMethodForPostGotByWrongID(String url , int id , JSONObject data){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            request.body(data.toJSONString());
            Response response = request.patch(url + id);
            Assert.assertEquals(404,response.getStatusCode(),"There is an error in rest api patch method");
        }


       public void validatePatchMethodForWrongDataPostGotByID(String url , int id , JSONObject data){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            request.body(data.toJSONString());
            Response response = request.patch(url + id);
            Assert.assertEquals(400,response.getStatusCode(),"There is an error in rest api patch method");
        }


       public void validateDeleteMethodForPostGotByID(String url , int id){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            Response response = request.delete(url + id);
            Assert.assertEquals(200,response.getStatusCode(),"There is an error in rest api delete method");
       }


       public void validateDeleteMethodForPostGotByWrongID(String url , int id){
            RequestSpecification request = RestAssured.given();
            request.header("Content-Type","application/json");
            Response response = request.delete(url + id);
            Assert.assertEquals(200,response.getStatusCode(),"There is an error in rest api delete method");
       }






}
