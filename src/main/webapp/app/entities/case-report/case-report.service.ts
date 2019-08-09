import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICaseReport } from 'app/shared/model/case-report.model';

type EntityResponseType = HttpResponse<ICaseReport>;
type EntityArrayResponseType = HttpResponse<ICaseReport[]>;

@Injectable({ providedIn: 'root' })
export class CaseReportService {
  public resourceUrl = SERVER_API_URL + 'api/case-reports';

  constructor(protected http: HttpClient) {}

  create(caseReport: ICaseReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(caseReport);
    return this.http
      .post<ICaseReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(caseReport: ICaseReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(caseReport);
    return this.http
      .put<ICaseReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICaseReport>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICaseReport[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(caseReport: ICaseReport): ICaseReport {
    const copy: ICaseReport = Object.assign({}, caseReport, {
      date: caseReport.date != null && caseReport.date.isValid() ? caseReport.date.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date != null ? moment(res.body.date) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((caseReport: ICaseReport) => {
        caseReport.date = caseReport.date != null ? moment(caseReport.date) : null;
      });
    }
    return res;
  }
}
