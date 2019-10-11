package com.criminalddbb.app.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ResourceUrl.
 */
@Entity
@Table(name = "resource_url")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ResourceUrl implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url_link")
    private String urlLink;

    @ManyToOne
    @JsonIgnore
    @JsonIgnoreProperties("resourceUrls")
    private CaseReport caseReport;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public ResourceUrl urlLink(String urlLink) {
        this.urlLink = urlLink;
        return this;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public CaseReport getCaseReport() {
        return caseReport;
    }

    public ResourceUrl caseReport(CaseReport caseReport) {
        this.caseReport = caseReport;
        return this;
    }

    public void setCaseReport(CaseReport caseReport) {
        this.caseReport = caseReport;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourceUrl)) {
            return false;
        }
        return id != null && id.equals(((ResourceUrl) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ResourceUrl{" +
            "id=" + getId() +
            ", urlLink='" + getUrlLink() + "'" +
            "}";
    }
}
