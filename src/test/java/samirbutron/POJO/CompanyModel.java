package samirbutron.POJO;

import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class CompanyModel {

  private String name;
  private String catchPhrase;
  private String bs;

  public CompanyModel(String name, String catchPhrase, String bs) {
    this.name = name;
    this.catchPhrase = catchPhrase;
    this.bs = bs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompanyModel companyModel = (CompanyModel) o;
    return Objects.equals(name, companyModel.name) &&
        Objects.equals(catchPhrase, companyModel.catchPhrase) &&
        Objects.equals(bs, companyModel.bs);
  }

  //Method created fo debugging
  @Override
  public String toString() {
    return "Company{" +
        "name='" + name + '\'' +
        ", catchPhrase='" + catchPhrase + '\'' +
        ", bs='" + bs + '\'' +
        '}';
  }
}
