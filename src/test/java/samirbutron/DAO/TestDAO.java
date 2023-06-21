package samirbutron.DAO;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "test")
@NoArgsConstructor
@AllArgsConstructor
public class TestDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private int id;

  @Column(name = "name", nullable = false)
  @Getter
  @Setter
  private String name;

  @Column(name = "status_id", nullable = true)
  @Getter
  @Setter
  private Integer status_id;

  @Column(name = "method_name", nullable = false)
  @Getter
  @Setter
  private String method_name;

  @Column(name = "project_id", nullable = false)
  @Getter
  @Setter
  private int project_id;

  @Column(name = "session_id", nullable = false)
  @Getter
  @Setter
  private int session_id;

  @Column(name = "start_time", nullable = true)
  @Getter
  @Setter
  private String start_time;

  @Column(name = "end_time", nullable = true)
  @Getter
  @Setter
  private String end_time;

  @Column(name = "env", nullable = false)
  @Getter
  @Setter
  private String env;

  @Column(name = "browser", nullable = true)
  @Getter
  @Setter
  private String browser;

  @Column(name = "author_id", nullable = true)
  @Getter
  @Setter
  private String author_id;

  @PrePersist
  public void prePersist() {
    if (name == null) {
      name = "emptyName";
    }
    if (method_name == null) {
      method_name = "emptyMethodName";
    }
    if (project_id == 0) {
      project_id = 1;
    }
    if (status_id == 0) {
      status_id = 1;
    }
    if (env == null) {
      env = "emptyEnv";
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TestDAO testDAO = (TestDAO) o;
    return Objects.equals(name, testDAO.name) &&
        Objects.equals(status_id, testDAO.status_id) &&
        Objects.equals(method_name, testDAO.method_name) &&
        Objects.equals(project_id, testDAO.project_id) &&
        Objects.equals(session_id, testDAO.session_id) &&
        Objects.equals(start_time, testDAO.start_time) &&
        Objects.equals(end_time, testDAO.end_time) &&
        Objects.equals(env, testDAO.env) &&
        Objects.equals(browser, testDAO.browser) &&
        Objects.equals(author_id, testDAO.author_id);
  }
}
