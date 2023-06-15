package samirbutron.POJO;

import java.util.Objects;

public class Post {

  private int userId;
  private int id;
  private String title;
  private String body;

  public Post() {

  }

  public Post(int userId, String title, String body) {
    this.userId = userId;
    this.title = title;
    this.body = body;
  }

  public Post(int userId, int id, String title, String body) {
    this.userId = userId;
    this.id = id;
    this.title = title;
    this.body = body;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Post post = (Post) o;
    return Objects.equals(userId, post.userId) &&
        Objects.equals(id, post.id) &&
        Objects.equals(title, post.title) &&
        Objects.equals(body, post.body);
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
