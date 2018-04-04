package springapp.dao.impl;

import org.springframework.stereotype.Repository;
import springapp.dao.CityDao;
import springapp.entities.CityEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CityDaoImpl extends BaseDao<CityEntity> implements CityDao {
    @Override
    public CityEntity getCityByName(String name) {
        List<CityEntity> cityEntity = getEntityManager()
                .createNamedQuery(CityEntity.QUERY_GET_CITY_BY_NAME, CityEntity.class)
                .setParameter("name", name.toUpperCase())
                .getResultList();

        return cityEntity.isEmpty() ? null : cityEntity.get(0);
    }
}
