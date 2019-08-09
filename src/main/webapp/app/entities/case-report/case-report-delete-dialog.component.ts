import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICaseReport } from 'app/shared/model/case-report.model';
import { CaseReportService } from './case-report.service';

@Component({
  selector: 'jhi-case-report-delete-dialog',
  templateUrl: './case-report-delete-dialog.component.html'
})
export class CaseReportDeleteDialogComponent {
  caseReport: ICaseReport;

  constructor(
    protected caseReportService: CaseReportService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.caseReportService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'caseReportListModification',
        content: 'Deleted an caseReport'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-case-report-delete-popup',
  template: ''
})
export class CaseReportDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ caseReport }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CaseReportDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.caseReport = caseReport;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/case-report', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/case-report', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
