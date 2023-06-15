package samirbutron.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import samirbutron.POJO.Post;

public class APIUtils {

  private static final ISettingsFile testConfig = new JsonSettingsFile("testconfig.json");
  private final static String url = testConfig.getValue("/url").toString();
  private final static String posts = "/posts";
  private final static String users = "/users";

  public static Response getAllPosts() {
    return RestAssured.given().baseUri(url).request().get(posts);
  }

  public static Response getPostById(int number) {
    return RestAssured.given().baseUri(url).request().get(posts + "/" + number);
  }

  public static Response doPost(Post post) {
    return RestAssured.given().baseUri(url).contentType(ContentType.JSON).body(post).post(posts);
  }

  public static Response getAllUsers() {
    return RestAssured.given().baseUri(url).request().get(users);
  }

  public static Response getUserById(int number) {
    return RestAssured.given().baseUri(url).request().get(users + "/" + number);
  }

}
