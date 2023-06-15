package samirbutron.POJO;

public class Geo {

  private float lat;
  private float lng;

  public Geo() {
  }

  public Geo(float lat, float lng) {
    this.lat = lat;
    this.lng = lng;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Geo geo = (Geo) o;
    return Float.compare(geo.lat, lat) == 0 &&
        Float.compare(geo.lng, lng) == 0;
  }

  //Method created fo debugging
  @Override
  public String toString() {
    return "Geo{" +
        "lat=" + lat +
        ", lng=" + lng +
        '}';
  }

  public float getLat() {
    return lat;
  }

  public void setLat(float lat) {
    this.lat = lat;
  }

  public float getLng() {
    return lng;
  }

  public void setLng(float lng) {
    this.lng = lng;
  }
}
