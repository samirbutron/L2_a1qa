package samirbutron.POJO;

import java.util.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class AddressModel {

  private String street;
  private String suite;
  private String city;
  private String zipcode;
  private GeoModel geoModel;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressModel addressModel = (AddressModel) o;
    return Objects.equals(street, addressModel.street) &&
        Objects.equals(suite, addressModel.suite) &&
        Objects.equals(city, addressModel.city) &&
        Objects.equals(zipcode, addressModel.zipcode) &&
        Objects.equals(geoModel, addressModel.geoModel);
  }

  //Method created fo debugging
  @Override
  public String toString() {
    return "Address{" +
        "street='" + street + '\'' +
        ", suite='" + suite + '\'' +
        ", city='" + city + '\'' +
        ", zipcode='" + zipcode + '\'' +
        ", geo=" + geoModel.toString() +
        '}';
  }
}
