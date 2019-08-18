import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CriminalddbbSharedModule } from 'app/shared';
import {
  CaseReportComponent,
  CaseReportDetailComponent,
  CaseReportUpdateComponent,
  CaseReportDeletePopupComponent,
  CaseReportDeleteDialogComponent,
  caseReportRoute,
  caseReportPopupRoute
} from './';

const ENTITY_STATES = [...caseReportRoute, ...caseReportPopupRoute];

@NgModule({
  imports: [CriminalddbbSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CaseReportComponent,
    CaseReportDetailComponent,
    CaseReportUpdateComponent,
    CaseReportDeleteDialogComponent,
    CaseReportDeletePopupComponent
  ],
  entryComponents: [CaseReportComponent, CaseReportUpdateComponent, CaseReportDeleteDialogComponent, CaseReportDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalddbbCaseReportModule {}
