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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "status_id", nullable = true)
  private Integer status_id;

  @Column(name = "method_name", nullable = false)
  private String method_name;

  @Column(name = "project_id", nullable = false)
  private int project_id;

  @Column(name = "session_id", nullable = false)
  private int session_id;

  @Column(name = "start_time", nullable = true)
  private String start_time;

  @Column(name = "end_time", nullable = true)
  private String end_time;

  @Column(name = "env", nullable = false)
  private String env;

  @Column(name = "browser", nullable = true)
  private String browser;

  @Column(name = "author_id", nullable = true)
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
