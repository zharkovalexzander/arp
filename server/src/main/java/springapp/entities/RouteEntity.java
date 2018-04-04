package springapp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "route", schema = "ctos")
public class RouteEntity {
    private Integer id;
    private String coordsList;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "coords_list")
    public String getCoordsList() {
        return coordsList;
    }

    public void setCoordsList(String coordsList) {
        this.coordsList = coordsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RouteEntity that = (RouteEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(coordsList, that.coordsList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, coordsList);
    }
}
