package samirbutron.test;

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
import samirbutron.POJO.PostModel;
import samirbutron.POJO.UserModel;
import samirbutron.utils.APIUtils;
import samirbutron.utils.RandomUtil;
import samirbutron.utils.TestConfigUtil;

public class APITest extends BaseTest {

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
    Assert.assertEquals(allPostsResponse.getStatusCode(), HttpStatus.SC_OK,
        "Status code is incorrect");
    //The list in response body is json
    Assert.assertTrue(allPostsResponse.contentType().startsWith(ContentType.JSON.toString()),
        "Response content type is not JSON");
    //Posts are sorted ascending (by id)
    List<PostModel> postsList = allPostsResponse.body().jsonPath().getList(".", PostModel.class);
    List<PostModel> orderedPostModels = new ArrayList<>(postsList);
    orderedPostModels.sort(Comparator.comparingInt(PostModel::getId));
    Assert.assertEquals(postsList, orderedPostModels, "Posts are not ordered");
    //STEP 2
    int validPost = TestConfigUtil.validPostGetId;
    int validPostId = TestConfigUtil.validPostGetUId;
    Response postByIdResponse = APIUtils.getPostById(validPost);
    //Status code is 200
    Assert.assertEquals(postByIdResponse.getStatusCode(), HttpStatus.SC_OK,
        "Status code incorrect");
    //Post information is correct: userId = 10, id = 99, (title && body != null)
    PostModel postModelById = postByIdResponse.body().as(PostModel.class);
    Assert.assertEquals(postModelById.getUserId(), validPostId, "User Id incorrect");
    Assert.assertEquals(postModelById.getId(), validPost, "Id incorrect");
    Assert.assertNotNull(postModelById.getTitle());
    Assert.assertNotNull(postModelById.getBody());
    //STEP 3
    int invalidPost = TestConfigUtil.invalidPostId;
    Response invalidPostResponse = APIUtils.getPostById(invalidPost);
    //Status code is 404
    Assert.assertEquals(invalidPostResponse.getStatusCode(), HttpStatus.SC_NOT_FOUND,
        "Status code incorrect");
    //Response body is empty
    Assert.assertEquals(invalidPostResponse.getBody().asString(), "{}",
        "Response body is not empty");
    //STEP 4
    String randomTitle = RandomUtil.generateString();
    String randomBody = RandomUtil.generateString();
    int postUserId = TestConfigUtil.postUserId;
    PostModel postModelPayload = new PostModel(postUserId, randomTitle, randomBody);
    Response postResponse = APIUtils.postToServer(postModelPayload);
    //Status code is 201
    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.SC_CREATED,
        "Status code is incorrect");
    //Post information is correct
    PostModel responsePostModel = postResponse.body().as(PostModel.class);
    //Maybe I could override Equals method for Post that just evaluates this 3 fields
    Assert.assertEquals(responsePostModel.getUserId(), postUserId, "User Id is different");
    Assert.assertEquals(responsePostModel.getTitle(), randomTitle, "Title is different");
    Assert.assertEquals(responsePostModel.getBody(), randomBody, "Body is different");
    //STEP 5
    String testUserString = TestConfigUtil.userString;
    int id = TestConfigUtil.getUserId;
    UserModel expectedUserModel = gson.fromJson(testUserString, UserModel.class);
    Response allUsersResponse = APIUtils.getAllUsers();
    List<UserModel> userModels = allUsersResponse.getBody().jsonPath()
        .getList(".", UserModel.class);
    UserModel actualUserFromListModel = null;
    for (UserModel userModel : userModels) {
      if (userModel.getId() == id) {
        actualUserFromListModel = userModel;
        break;
      }
    }
    //Status code is 200
    Assert.assertEquals(allUsersResponse.getStatusCode(), HttpStatus.SC_OK,
        "Status code is incorrect");
    //The list in response body is JSON
    Assert.assertTrue(allUsersResponse.contentType().startsWith(ContentType.JSON.toString()),
        "Response content type is not JSON");
    //User data equals to: (something)
    Assert.assertEquals(expectedUserModel, actualUserFromListModel,
        "User object is different from expected");
    //STEP 6
    Response userByIdResponse = APIUtils.getUserById(id);
    UserModel actualUserByIdModel = userByIdResponse.body().as(UserModel.class);
    //Status code is 200
    Assert.assertEquals(userByIdResponse.getStatusCode(), HttpStatus.SC_OK,
        "Status code incorrect");
    //User data matches with user data in previous step
    Assert.assertEquals(actualUserFromListModel, actualUserByIdModel,
        "User object is different from expected");
  }
}
