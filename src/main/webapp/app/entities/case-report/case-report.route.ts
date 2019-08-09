import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CaseReport } from 'app/shared/model/case-report.model';
import { CaseReportService } from './case-report.service';
import { CaseReportComponent } from './case-report.component';
import { CaseReportDetailComponent } from './case-report-detail.component';
import { CaseReportUpdateComponent } from './case-report-update.component';
import { CaseReportDeletePopupComponent } from './case-report-delete-dialog.component';
import { ICaseReport } from 'app/shared/model/case-report.model';

@Injectable({ providedIn: 'root' })
export class CaseReportResolve implements Resolve<ICaseReport> {
  constructor(private service: CaseReportService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICaseReport> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CaseReport>) => response.ok),
        map((caseReport: HttpResponse<CaseReport>) => caseReport.body)
      );
    }
    return of(new CaseReport());
  }
}

export const caseReportRoute: Routes = [
  {
    path: '',
    component: CaseReportComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'criminalBbddApp.caseReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CaseReportDetailComponent,
    resolve: {
      caseReport: CaseReportResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'criminalBbddApp.caseReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CaseReportUpdateComponent,
    resolve: {
      caseReport: CaseReportResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'criminalBbddApp.caseReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CaseReportUpdateComponent,
    resolve: {
      caseReport: CaseReportResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'criminalBbddApp.caseReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const caseReportPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CaseReportDeletePopupComponent,
    resolve: {
      caseReport: CaseReportResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'criminalBbddApp.caseReport.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
