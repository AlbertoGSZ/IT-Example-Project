package org.jhipster.criminalddbb.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ResourceURL.
 */
@Entity
@Table(name = "resource_url")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ResourceURL implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JsonIgnoreProperties("resourceURLS")
    private CaseReport caseReport;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public ResourceURL url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CaseReport getCaseReport() {
        return caseReport;
    }

    public ResourceURL caseReport(CaseReport caseReport) {
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
        if (!(o instanceof ResourceURL)) {
            return false;
        }
        return id != null && id.equals(((ResourceURL) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ResourceURL{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            "}";
    }
}
