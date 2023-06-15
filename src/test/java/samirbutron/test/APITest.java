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
  public void test1() {
    Response response = APIUtils.getAllPosts();
    //Status code is 200
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is incorrect");
    //The list in response body is json
    Assert.assertTrue(response.contentType().startsWith(ContentType.JSON.toString()),
        "Response content type is not JSON");
    //Posts are sorted ascending (by id)
    List<Post> posts = POJOUtil.convertToPOJOPosts(response);
    List<Post> orderedPosts = new ArrayList<>(posts);
    orderedPosts.sort(Comparator.comparingInt(Post::getId));
    Assert.assertEquals(posts, orderedPosts, "Posts are not ordered");
  }

  @Test
  public void test2() {
    int validPost = (int) testConfig.getValue("/valid post");
    int validPostId = (int) testConfig.getValue("/valid post Uid");
    Response response = APIUtils.getPostById(validPost);
    //Status code is 200
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code incorrect");
    //Post information is correct: userId = 10, id = 99, (title && body != null)
    Post post = POJOUtil.convertToPOJOPost(response);
    Assert.assertEquals(post.getUserId(), validPostId, "User Id incorrect");
    Assert.assertEquals(post.getId(), validPost, "Id incorrect");
    Assert.assertNotNull(post.getTitle());
    Assert.assertNotNull(post.getBody());
  }

  @Test
  public void test3() {
    int invalidPost = (int) testConfig.getValue("/invalid post");
    Response response = APIUtils.getPostById(invalidPost);
    //Status code is 404
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Status code incorrect");
    //Response body is empty
    Assert.assertEquals(response.getBody().asString(), "{}", "Response body is not empty");
  }

  @Test
  public void test4() {
    String randomTitle = RandomUtil.generateString();
    String randomBody = RandomUtil.generateString();
    int userId = (int) testConfig.getValue("/post UserId");
    Post post = new Post(userId, randomTitle, randomBody);
    Response response = APIUtils.doPost(post);
    //Status code is 201
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED,
        "Status code is incorrect");
    //Post information is correct
    Post responsePost = POJOUtil.convertToPOJOPost(response);
    //Maybe I could override Equals method for Post that just evaluates this 3 fields
    Assert.assertEquals(responsePost.getUserId(), userId, "User Id is different");
    Assert.assertEquals(responsePost.getTitle(), randomTitle, "Title is different");
    Assert.assertEquals(responsePost.getBody(), randomBody, "Body is different");

  }

  @Test
  public void test5() {
    String user5String = testConfig.getValue("/user5").toString();
    int id = (int) testConfig.getValue("/get id");
    User expectedUser = gson.fromJson(user5String, User.class);
    Response response = APIUtils.getAllUsers();
    List<User> users = POJOUtil.convertToPOJOUsers(response);
    User actualUser = users.get(id - 1);

    //Status code is 200
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code is incorrect");
    //The list in response body is JSON
    Assert.assertTrue(response.contentType().startsWith(ContentType.JSON.toString()),
        "Response content type is not JSON");
    //User data equals to: (something)
    Assert.assertTrue(actualUser.equals(expectedUser));

  }

  @Test
  public void test6() {
    int id = (int) testConfig.getValue("/get id");
    String user5String = testConfig.getValue("/user5").toString();
    User expectedUser = gson.fromJson(user5String, User.class);
    Response response = APIUtils.getUserById(id);
    User actualUser = POJOUtil.convertToPOJOUser(response);
    //Status code is 200
    Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code incorrect");
    //User data matches with user data in previous step
    Assert.assertTrue(actualUser.equals(expectedUser));


  }


}
