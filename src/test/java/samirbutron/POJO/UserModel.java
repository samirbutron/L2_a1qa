package samirbutron.POJO;

import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class UserModel {

  private int id;
  private String name;
  private String username;
  private String email;
  private AddressModel addressModel;
  private String phone;
  private String website;
  private CompanyModel companyModel;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserModel userModel = (UserModel) o;
    return id == userModel.id &&
        Objects.equals(name, userModel.name) &&
        Objects.equals(username, userModel.username) &&
        Objects.equals(email, userModel.email) &&
        Objects.equals(addressModel, userModel.addressModel) &&
        Objects.equals(phone, userModel.phone) &&
        Objects.equals(website, userModel.website) &&
        Objects.equals(companyModel, userModel.companyModel);
  }

  //Method created fo debugging
  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", address=" + addressModel.toString() +
        ", phone='" + phone + '\'' +
        ", website='" + website + '\'' +
        ", company=" + companyModel.toString() +
        '}';
  }
}