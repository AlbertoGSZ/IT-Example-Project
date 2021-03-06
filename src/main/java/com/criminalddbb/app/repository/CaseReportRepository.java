package com.criminalddbb.app.repository;

import com.criminalddbb.app.domain.CaseReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaseReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaseReportRepository extends JpaRepository<CaseReport, Long> {

}
