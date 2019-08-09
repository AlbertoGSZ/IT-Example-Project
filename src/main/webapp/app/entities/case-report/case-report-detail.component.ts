import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICaseReport } from 'app/shared/model/case-report.model';

@Component({
  selector: 'jhi-case-report-detail',
  templateUrl: './case-report-detail.component.html'
})
export class CaseReportDetailComponent implements OnInit {
  caseReport: ICaseReport;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ caseReport }) => {
      this.caseReport = caseReport;
    });
  }

  previousState() {
    window.history.back();
  }
}
