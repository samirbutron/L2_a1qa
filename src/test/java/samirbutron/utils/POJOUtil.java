package samirbutron.utils;

import io.restassured.response.Response;
import java.util.List;
import samirbutron.POJO.Post;
import samirbutron.POJO.User;

public class POJOUtil {

  public static Post convertToPOJOPost(Response response) {
    return response.body().as(Post.class);
  }

  public static List<Post> convertToPOJOPosts(Response response) {
    return response.getBody().jsonPath().getList(".", Post.class);
  }

  public static User convertToPOJOUser(Response response) {
    return response.body().as(User.class);
  }

  public static List<User> convertToPOJOUsers(Response response) {
    return response.getBody().jsonPath().getList(".", User.class);
  }
}
