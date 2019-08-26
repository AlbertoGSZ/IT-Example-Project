package org.jhipster.criminalddbb.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.jhipster.criminalddbb.domain.enumeration.Nationality;

import org.jhipster.criminalddbb.domain.enumeration.Sex;
import org.jhipster.criminalddbb.domain.enumeration.Status;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private Instant birthDate;

    @Column(name = "age")
    private Integer age;

    @Column(name = "adress")
    private String adress;

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality")
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "alias")
    private String alias;

    @Column(name = "jhi_rank")
    private Integer rank;

    @OneToMany(mappedBy = "chief")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Person> subordinates = new HashSet<>();

    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CaseReport> felonyRecordsDigitals = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("subordinates")
    private Person chief;

    public Person(){}

    public Person(Long id, String name, String surname, Integer age, Sex sex, String alias, Status status, String adress, Nationality nationality, Integer rank) {
    this.id=id;
    this.name=name;
    this.surname=surname;
    this.age=age;
    this.sex=sex;
    this.alias=alias;
    this.status=status;
    this.adress=adress;
    this.nationality=nationality;
    this.rank=rank;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public Person surname(String surname) {
        this.surname = surname;
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public Person birthDate(Instant birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public Person age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAdress() {
        return adress;
    }

    public Person adress(String adress) {
        this.adress = adress;
        return this;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public Person nationality(Nationality nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Status getStatus() {
        return status;
    }

    public Person status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Sex getSex() {
        return sex;
    }

    public Person sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getAlias() {
        return alias;
    }

    public Person alias(String alias) {
        this.alias = alias;
        return this;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getRank() {
        return rank;
    }

    public Person rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Set<Person> getSubordinates() {
        return subordinates;
    }

    public Person subordinates(Set<Person> people) {
        this.subordinates = people;
        return this;
    }

    public Person addSubordinates(Person person) {
        this.subordinates.add(person);
        person.setChief(this);
        return this;
    }

    public Person removeSubordinates(Person person) {
        this.subordinates.remove(person);
        person.setChief(null);
        return this;
    }

    public void setSubordinates(Set<Person> people) {
        this.subordinates = people;
    }

    public Set<CaseReport> getFelonyRecordsDigitals() {
        return felonyRecordsDigitals;
    }

    public Person felonyRecordsDigitals(Set<CaseReport> caseReports) {
        this.felonyRecordsDigitals = caseReports;
        return this;
    }

    public Person addFelonyRecordsDigital(CaseReport caseReport) {
        this.felonyRecordsDigitals.add(caseReport);
        caseReport.setPerson(this);
        return this;
    }

    public Person removeFelonyRecordsDigital(CaseReport caseReport) {
        this.felonyRecordsDigitals.remove(caseReport);
        caseReport.setPerson(null);
        return this;
    }

    public void setFelonyRecordsDigitals(Set<CaseReport> caseReports) {
        this.felonyRecordsDigitals = caseReports;
    }

    public Person getChief() {
        return chief;
    }

    public Person chief(Person person) {
        this.chief = person;
        return this;
    }

    public void setChief(Person person) {
        this.chief = person;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public HashMap<String,Object> toHashMap(){
        HashMap <String,Object> hash = new HashMap<>();

        if (this.id!=null) hash.put("id", this.getId());
        if (this.name!=null) hash.put("name",this.getName());
        if (this.status!=null) hash.put("status", this.getStatus());
        if (this.surname!=null) hash.put("surname",this.getSurname());
        if (this.age!=null) hash.put("age",this.getAge());
        if (this.adress!=null) hash.put("adress",this.getAdress());
        if (this.alias!=null) hash.put("alias",this.getAlias());
        if (this.nationality!=null) hash.put("nationality", this.getNationality());
        if (this.sex!=null) hash.put("sex", this.getSex());
        if (this.rank!=null) hash.put("rank", this.getRank());
        return hash;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        return id != null && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", surname='" + getSurname() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", age=" + getAge() +
            ", status=" + getStatus() +
            ", adress='" + getAdress() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", sex='" + getSex() + "'" +
            ", alias='" + getAlias() + "'" +
            ", rank=" + getRank() +
            "}";
    }
}
