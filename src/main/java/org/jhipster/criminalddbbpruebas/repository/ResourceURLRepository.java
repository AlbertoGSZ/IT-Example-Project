package org.jhipster.criminalddbbpruebas.repository;

import org.jhipster.criminalddbbpruebas.domain.ResourceURL;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResourceURL entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceURLRepository extends JpaRepository<ResourceURL, Long> {

}
