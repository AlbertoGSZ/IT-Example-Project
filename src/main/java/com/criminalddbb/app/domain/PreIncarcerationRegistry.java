package com.criminalddbb.app.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A PreIncarcerationRegistry.
 */
@Entity
@Table(name = "pre_incarceration_registry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PreIncarcerationRegistry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "old_rank")
    private Integer oldRank;

    @Column(name = "old_chief")
    private Long oldChief;

    @Column(name = "old_subordinates")
    private String oldSubordinates;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOldRank() {
        return oldRank;
    }

    public PreIncarcerationRegistry oldRank(Integer oldRank) {
        this.oldRank = oldRank;
        return this;
    }

    public void setOldRank(Integer oldRank) {
        this.oldRank = oldRank;
    }

    public Long getOldChief() {
        return oldChief;
    }

    public PreIncarcerationRegistry oldChief(Long oldChief) {
        this.oldChief = oldChief;
        return this;
    }

    public void setOldChief(Long oldChief) {
        this.oldChief = oldChief;
    }

    public String getOldSubordinates() {
        return oldSubordinates;
    }

    public PreIncarcerationRegistry oldSubordinates(String oldSubordinates) {
        this.oldSubordinates = oldSubordinates;
        return this;
    }

    public void setOldSubordinates(String oldSubordinates) {
        this.oldSubordinates = oldSubordinates;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PreIncarcerationRegistry)) {
            return false;
        }
        return id != null && id.equals(((PreIncarcerationRegistry) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PreIncarcerationRegistry{" +
            "id=" + getId() +
            ", oldRank=" + getOldRank() +
            ", oldChief=" + getOldChief() +
            ", oldSubordinates='" + getOldSubordinates() + "'" +
            "}";
    }
}
