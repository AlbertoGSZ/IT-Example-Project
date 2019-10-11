package com.criminalddbb.app.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.internal.loader.Resource;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A CaseReport.
 */
@Entity
@Table(name = "case_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CaseReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Instant date;

    @Column(name = "mugshot_url")
    private String mugshotURL;

    @Column(name = "person_details")
    private String personDetails;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "evidence_photos_url")
    private String evidencePhotosURL;

    @OneToMany(mappedBy = "caseReport")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ResourceUrl> felonyRecordsPDFsURLs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("felonyRecordsDigitals")
    private Person caseReportHolder;

    public CaseReport() {
        this.felonyRecordsPDFsURLs = new HashSet<>();
    }

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

    public String getMugshotURL() {
        return mugshotURL;
    }

    public CaseReport mugshotURL(String mugshotURL) {
        this.mugshotURL = mugshotURL;
        return this;
    }

    public void setMugshotURL(String mugshotURL) {
        this.mugshotURL = mugshotURL;
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

    public String getEvidencePhotosURL() {
        return evidencePhotosURL;
    }

    public CaseReport evidencePhotosURL(String evidencePhotosURL) {
        this.evidencePhotosURL = evidencePhotosURL;
        return this;
    }

    public void setEvidencePhotosURL(String evidencePhotosURL) {
        this.evidencePhotosURL = evidencePhotosURL;
    }

    public Set<ResourceUrl> getFelonyRecordsPDFsURLs() {
        return felonyRecordsPDFsURLs;
    }

    public CaseReport felonyRecordsPDFsURLs(Set<ResourceUrl> resourceUrls) {
        this.felonyRecordsPDFsURLs = resourceUrls;
        return this;
    }

    public CaseReport addFelonyRecordsPDFsURLs(ResourceUrl resourceUrl) {
        this.felonyRecordsPDFsURLs=new HashSet<>();
        resourceUrl.setCaseReport(this);
        return this;
    }

    public CaseReport removeFelonyRecordsPDFsURLs(ResourceUrl resourceUrl) {
        this.felonyRecordsPDFsURLs.remove(resourceUrl);
        resourceUrl.setCaseReport(null);
        return this;
    }

    public CaseReport eliminateResourceUrl(Long id) {
        ResourceUrl Aux = this.felonyRecordsPDFsURLs.stream().filter(x->x.getId()==id).collect(Collectors.toList()).get(0);
        this.felonyRecordsPDFsURLs.remove(Aux);
        return this;
    }

    public void setFelonyRecordsPDFsURLs(Set<ResourceUrl> resourceUrls) {
        this.felonyRecordsPDFsURLs = resourceUrls;
    }

    public Person getCaseReportHolder() {
        return caseReportHolder;
    }

    public CaseReport caseReportHolder(Person person) {
        this.caseReportHolder = person;
        return this;
    }

    public void setCaseReportHolder(Person person) {
        this.caseReportHolder = person;
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
            ", mugshotURL='" + getMugshotURL() + "'" +
            ", personDetails='" + getPersonDetails() + "'" +
            ", eventDescription='" + getEventDescription() + "'" +
            ", evidencePhotosURL='" + getEvidencePhotosURL() + "'" +
            "}";
    }



}
