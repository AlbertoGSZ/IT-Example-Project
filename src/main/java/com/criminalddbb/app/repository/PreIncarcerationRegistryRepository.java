package com.criminalddbb.app.repository;

import com.criminalddbb.app.domain.PreIncarcerationRegistry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PreIncarcerationRegistry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreIncarcerationRegistryRepository extends JpaRepository<PreIncarcerationRegistry, Long> {

}
