package springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springapp.dao.CityDao;
import springapp.entities.CityEntity;

import javax.transaction.Transactional;

@Service
@Transactional
public class CityService {

    @Autowired
    private CityDao cityDao;

    public CityEntity getCityByName(String name) {
        return cityDao.getCityByName(name);
    }
}
