package samirbutron.POJO;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class UserModel {

  @Getter
  @Setter
  private int id;
  @Getter
  @Setter
  private String name;
  @Getter
  @Setter
  private String username;
  @Getter
  @Setter
  private String email;
  @Getter
  @Setter
  private AddressModel addressModel;
  @Getter
  @Setter
  private String phone;
  @Getter
  @Setter
  private String website;
  @Getter
  @Setter
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