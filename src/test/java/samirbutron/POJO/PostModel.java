package samirbutron.POJO;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class PostModel {

  @Getter
  @Setter
  private int userId;
  @Getter
  @Setter
  private int id;
  @Getter
  @Setter
  private String title;
  @Getter
  @Setter
  private String body;

  public PostModel(int userId, String title, String body) {
    this.userId = userId;
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
    PostModel postModel = (PostModel) o;
    return Objects.equals(userId, postModel.userId) &&
        Objects.equals(id, postModel.id) &&
        Objects.equals(title, postModel.title) &&
        Objects.equals(body, postModel.body);
  }
}
