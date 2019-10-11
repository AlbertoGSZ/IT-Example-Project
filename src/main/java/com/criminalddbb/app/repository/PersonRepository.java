package com.criminalddbb.app.repository;

import com.criminalddbb.app.domain.Person;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


/**
 * Spring Data  repository for the Person entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    public List<Person> getData (HashMap<String,Object> hash);

}
