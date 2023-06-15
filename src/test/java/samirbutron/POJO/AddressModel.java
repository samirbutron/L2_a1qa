package samirbutron.POJO;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class AddressModel {

  @Getter
  @Setter
  private String street;
  @Getter
  @Setter
  private String suite;
  @Getter
  @Setter
  private String city;
  @Getter
  @Setter
  private String zipcode;
  @Getter
  @Setter
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
