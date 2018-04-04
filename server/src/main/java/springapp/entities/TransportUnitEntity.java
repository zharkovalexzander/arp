package springapp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transport_unit", schema = "ctos")
public class TransportUnitEntity {
    private Integer id;
    private String transportNumber;
    private String type;
    private Double price;
    private Integer seats;
    private Integer capacity;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(transportNumber, that.transportNumber) &&
                Objects.equals(type, that.type) &&
                Objects.equals(price, that.price) &&
                Objects.equals(seats, that.seats) &&
                Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, transportNumber, type, price, seats, capacity);
    }
}
