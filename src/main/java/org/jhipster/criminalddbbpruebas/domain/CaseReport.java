package org.jhipster.criminalddbbpruebas.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A CaseReport.
 */
@Entity
@Table(name = "case_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date")
    private Instant date;

    @Column(name = "mugshot")
    private String mugshot;

    @Column(name = "person_details")
    private String personDetails;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "evidence_photos")
    private String evidencePhotos;

    @ManyToOne
    @JsonIgnoreProperties("caseReports")
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public CaseReport date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getMugshot() {
        return mugshot;
    }

    public CaseReport mugshot(String mugshot) {
        this.mugshot = mugshot;
        return this;
    }

    public void setMugshot(String mugshot) {
        this.mugshot = mugshot;
    }

    public String getPersonDetails() {
        return personDetails;
    }

    public CaseReport personDetails(String personDetails) {
        this.personDetails = personDetails;
        return this;
    }

    public void setPersonDetails(String personDetails) {
        this.personDetails = personDetails;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public CaseReport eventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        return this;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEvidencePhotos() {
        return evidencePhotos;
    }

    public CaseReport evidencePhotos(String evidencePhotos) {
        this.evidencePhotos = evidencePhotos;
        return this;
    }

    public void setEvidencePhotos(String evidencePhotos) {
        this.evidencePhotos = evidencePhotos;
    }

    public Person getPerson() {
        return person;
    }

    public CaseReport person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CaseReport)) {
            return false;
        }
        return id != null && id.equals(((CaseReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CaseReport{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", mugshot='" + getMugshot() + "'" +
            ", personDetails='" + getPersonDetails() + "'" +
            ", eventDescription='" + getEventDescription() + "'" +
            ", evidencePhotos='" + getEvidencePhotos() + "'" +
            "}";
    }
}
