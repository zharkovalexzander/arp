package springapp.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TransportUnitEntityPK implements Serializable {
    private Long cityId;
    private Long routeId;

    @Column(name = "city_id")
    @Id
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Column(name = "route_id")
    @Id
    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportUnitEntityPK that = (TransportUnitEntityPK) o;
        return Objects.equals(cityId, that.cityId) &&
                Objects.equals(routeId, that.routeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cityId, routeId);
    }
}
