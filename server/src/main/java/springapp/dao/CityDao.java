package springapp.dao;

import springapp.entities.CityEntity;

public interface CityDao {
    CityEntity getCityByName(String name);
}
