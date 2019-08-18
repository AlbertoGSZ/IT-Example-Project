package org.jhipster.criminalddbb.repository;

import org.jhipster.criminalddbb.domain.ResourceURL;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResourceURL entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceURLRepository extends JpaRepository<ResourceURL, Long> {

}
