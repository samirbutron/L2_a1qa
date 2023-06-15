package samirbutron.test;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.hc.core5.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import samirbutron.POJO.Post;
import samirbutron.POJO.User;
import samirbutron.utils.APIUtils;
import samirbutron.utils.POJOUtil;
import samirbutron.utils.RandomUtil;

public class APITest extends BaseTest {

  private static final ISettingsFile testConfig = new JsonSettingsFile("testconfig.json");
  private static final Gson gson = new Gson();

  @BeforeMethod
  public void testStart() {
    setWebPage();
  }

  @AfterMethod
  public void testEnd() {
    tearDown();
  }

  //
  @Test
  public void test() {
    //STEP 1
    Response allPostsResponse = APIUtils.getAllPosts();
    //Status code is 200
    Assert.assertEquals(allPostsResponse.getStatusCode(), HttpStatus.SC_OK, "Status code is incorrect");
    //The list in response body is json
    Assert.assertTrue(allPostsResponse.contentType().startsWith(ContentType.JSON.toString()),
        "Response content type is not JSON");
    //Posts are sorted ascending (by id)
    List<Post> postsList = POJOUtil.convertToPOJOPosts(allPostsResponse);
    List<Post> orderedPosts = new ArrayList<>(postsList);
    orderedPosts.sort(Comparator.comparingInt(Post::getId));
    Assert.assertEquals(postsList, orderedPosts, "Posts are not ordered");
    //STEP 2
    int validPost = (int) testConfig.getValue("/valid post");
    int validPostId = (int) testConfig.getValue("/valid post Uid");
    Response postByIdResponse = APIUtils.getPostById(validPost);
    //Status code is 200
    Assert.assertEquals(postByIdResponse.getStatusCode(), HttpStatus.SC_OK, "Status code incorrect");
    //Post information is correct: userId = 10, id = 99, (title && body != null)
    Post postById = POJOUtil.convertToPOJOPost(postByIdResponse);
    Assert.assertEquals(postById.getUserId(), validPostId, "User Id incorrect");
    Assert.assertEquals(postById.getId(), validPost, "Id incorrect");
    Assert.assertNotNull(postById.getTitle());
    Assert.assertNotNull(postById.getBody());
    //STEP 3
    int invalidPost = (int) testConfig.getValue("/invalid post");
    Response invalidPostResponse = APIUtils.getPostById(invalidPost);
    //Status code is 404
    Assert.assertEquals(invalidPostResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code incorrect");
    //Response body is empty
    Assert.assertEquals(invalidPostResponse.getBody().asString(), "{}", "Response body is not empty");
    //STEP 4
    String randomTitle = RandomUtil.generateString();
    String randomBody = RandomUtil.generateString();
    int postUserId = (int) testConfig.getValue("/post UserId");
    Post postPayload = new Post(postUserId, randomTitle, randomBody);
    Response postResponse = APIUtils.doPost(postPayload);
    //Status code is 201
    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.SC_CREATED,
        "Status code is incorrect");
    //Post information is correct
    Post responsePost = POJOUtil.convertToPOJOPost(postResponse);
    //Maybe I could override Equals method for Post that just evaluates this 3 fields
    Assert.assertEquals(responsePost.getUserId(), postUserId, "User Id is different");
    Assert.assertEquals(responsePost.getTitle(), randomTitle, "Title is different");
    Assert.assertEquals(responsePost.getBody(), randomBody, "Body is different");
    //STEP 5
    String user5String = testConfig.getValue("/user5").toString();
    int id = (int) testConfig.getValue("/get id");
    User expectedUser = gson.fromJson(user5String, User.class);
    Response allUsersResponse = APIUtils.getAllUsers();
    List<User> users = POJOUtil.convertToPOJOUsers(allUsersResponse);
    User actualUserFromList = users.get(id - 1);
    //Status code is 200
    Assert.assertEquals(allUsersResponse.getStatusCode(), HttpStatus.SC_OK, "Status code is incorrect");
    //The list in response body is JSON
    Assert.assertTrue(allUsersResponse.contentType().startsWith(ContentType.JSON.toString()),
        "Response content type is not JSON");
    //User data equals to: (something)
    Assert.assertTrue(actualUserFromList.equals(expectedUser));
    //STEP 6
    Response userByIdResponse = APIUtils.getUserById(id);
    User actualUserById = POJOUtil.convertToPOJOUser(userByIdResponse);
    //Status code is 200
    Assert.assertEquals(userByIdResponse.getStatusCode(), HttpStatus.SC_OK, "Status code incorrect");
    //User data matches with user data in previous step
    Assert.assertTrue(actualUserById.equals(actualUserFromList));
  }
}
