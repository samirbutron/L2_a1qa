package samirbutron.POJO;

import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class PostModel {

  private int userId;
  private int id;
  private String title;
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
