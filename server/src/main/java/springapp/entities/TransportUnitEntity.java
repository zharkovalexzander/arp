package springapp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transport_unit", schema = "ctos", catalog = "")
@IdClass(TransportUnitEntityPK.class)
public class TransportUnitEntity {
    private Long cityId;
    private Long routeId;
    private String transportNumber;
    private String type;
    private Double price;
    private Integer seats;
    private Integer capacity;

    @Id
    @Column(name = "city_id")
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Id
    @Column(name = "route_id")
    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    @Basic
    @Column(name = "transport_number")
    public String getTransportNumber() {
        return transportNumber;
    }

    public void setTransportNumber(String transportNumber) {
        this.transportNumber = transportNumber;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "seats")
    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Basic
    @Column(name = "capacity")
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransportUnitEntity that = (TransportUnitEntity) o;
        return Objects.equals(cityId, that.cityId) &&
                Objects.equals(routeId, that.routeId) &&
                Objects.equals(transportNumber, that.transportNumber) &&
                Objects.equals(type, that.type) &&
                Objects.equals(price, that.price) &&
                Objects.equals(seats, that.seats) &&
                Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cityId, routeId, transportNumber, type, price, seats, capacity);
    }
}
