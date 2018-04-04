package springapp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@NamedQueries(value = {
        @NamedQuery(name = CityEntity.QUERY_GET_CITY_BY_NAME,
                query = "select c from CityEntity c where c.name = :name")
})

@Entity
@Table(name = "city", schema = "ctos")
public class CityEntity extends BasicEntity implements Serializable {
    public static final String QUERY_GET_CITY_BY_NAME = "CityEntity.getCityByName";

    private Integer id;
    private String name;
    private String country;
    private String info;
    private String coords;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "coords")
    public String getCoords() {
        return coords;
    }

    public void setCoords(String coords) {
        this.coords = coords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country) &&
                Objects.equals(info, that.info) &&
                Objects.equals(coords, that.coords);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, country, info, coords);
    }

    @Override
    public String entityName() {
        return "CityEntity";
    }
}
