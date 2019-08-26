package org.jhipster.criminalddbb.repository.impl;
import org.jhipster.criminalddbb.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PersonRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getData (HashMap<String,String>hash){
        CriteriaBuilder cb = entityManager .getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from (Person. class);
        List<Predicate>predicates = new ArrayList<>();
        hash.forEach ((field,value) -> {
            switch (field) {
                case "id":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "status":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "name":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "adress":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "age":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "alias":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "nationality":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "sex":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "rank":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
            }
        });
        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));


        System.out.println("\n\n\n --------------------------------------------------------1 \n\n");
        System.out.println(query.toString());
        System.out.println("\n ------------------------------------------------------------2 \n");
        System.out.println(entityManager.createQuery(query)); //    <- ERROR EN ESTA LÍNEA, AL CREAR LA QUERY
        System.out.println("\n ------------------------------------------------------------3 \n");
        System.out.println(entityManager.createQuery(query).getResultList());
        System.out.println("\n\n\n --------------------------------------------------------4 \n\n");

        return entityManager.createQuery(query).getResultList();
    }


}
