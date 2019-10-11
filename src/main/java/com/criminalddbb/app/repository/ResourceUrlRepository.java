package com.criminalddbb.app.repository;

import com.criminalddbb.app.domain.ResourceUrl;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResourceUrl entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourceUrlRepository extends JpaRepository<ResourceUrl, Long> {

}
