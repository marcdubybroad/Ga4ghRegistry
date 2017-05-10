package org.ga4gh.registry.datastore.jpa;

import org.ga4gh.registry.bean.ServerNodeBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface to extend the JPA methods
 *
 * Created by mduby on 5/10/17.
 */
@Repository
public interface RegistryRepository extends CrudRepository<ServerNodeBean, Long> {
}
