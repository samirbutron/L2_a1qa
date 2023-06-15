package samirbutron.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import samirbutron.POJO.PostModel;

public class APIUtils {

  private final static String url = TestConfigUtil.url;
  private final static String posts = TestConfigUtil.postsEndPoint;
  private final static String users = TestConfigUtil.usersEndPoint;

  public static Response getAllPosts() {
    return RestAssured.given().baseUri(url).request().get(posts);
  }

  public static Response getPostById(int number) {
    return RestAssured.given().baseUri(url).request().get(posts + "/" + number);
  }

  public static Response postToServer(PostModel postModel) {
    return RestAssured.given().baseUri(url).contentType(ContentType.JSON).body(postModel)
        .post(posts);
  }

  public static Response getAllUsers() {
    return RestAssured.given().baseUri(url).request().get(users);
  }

  public static Response getUserById(int number) {
    return RestAssured.given().baseUri(url).request().get(users + "/" + number);
  }

}
