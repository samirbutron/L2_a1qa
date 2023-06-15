package samirbutron.POJO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class GeoModel {

  @Getter
  @Setter
  private float lat;
  @Getter
  @Setter
  private float lng;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GeoModel geoModel = (GeoModel) o;
    return Float.compare(geoModel.lat, lat) == 0 &&
        Float.compare(geoModel.lng, lng) == 0;
  }

  //Method created fo debugging
  @Override
  public String toString() {
    return "Geo{" +
        "lat=" + lat +
        ", lng=" + lng +
        '}';
  }
}
