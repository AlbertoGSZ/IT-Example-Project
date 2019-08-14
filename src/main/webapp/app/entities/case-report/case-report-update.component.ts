import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ICaseReport, CaseReport } from 'app/shared/model/case-report.model';
import { CaseReportService } from './case-report.service';
import { IPerson } from 'app/shared/model/person.model';
import { PersonService } from 'app/entities/person';
import { IResourceURL } from 'app/shared/model/resource-url.model';
import { ResourceURLService } from 'app/entities/resource-url';

@Component({
  selector: 'jhi-case-report-update',
  templateUrl: './case-report-update.component.html'
})
export class CaseReportUpdateComponent implements OnInit {
  isSaving: boolean;

  people: IPerson[];

  mugshotreports: IResourceURL[];

  editForm = this.fb.group({
    id: [],
    date: [],
    personDetails: [],
    eventDescription: [],
    person: [],
    mugshotReport: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected caseReportService: CaseReportService,
    protected personService: PersonService,
    protected resourceURLService: ResourceURLService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ caseReport }) => {
      this.updateForm(caseReport);
    });
    this.personService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPerson[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPerson[]>) => response.body)
      )
      .subscribe((res: IPerson[]) => (this.people = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.resourceURLService
      .query({ filter: 'casereport-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IResourceURL[]>) => mayBeOk.ok),
        map((response: HttpResponse<IResourceURL[]>) => response.body)
      )
      .subscribe(
        (res: IResourceURL[]) => {
          if (!this.editForm.get('mugshotReport').value || !this.editForm.get('mugshotReport').value.id) {
            this.mugshotreports = res;
          } else {
            this.resourceURLService
              .find(this.editForm.get('mugshotReport').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IResourceURL>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IResourceURL>) => subResponse.body)
              )
              .subscribe(
                (subRes: IResourceURL) => (this.mugshotreports = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(caseReport: ICaseReport) {
    this.editForm.patchValue({
      id: caseReport.id,
      date: caseReport.date != null ? caseReport.date.format(DATE_TIME_FORMAT) : null,
      personDetails: caseReport.personDetails,
      eventDescription: caseReport.eventDescription,
      person: caseReport.person,
      mugshotReport: caseReport.mugshotReport
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const caseReport = this.createFromForm();
    if (caseReport.id !== undefined) {
      this.subscribeToSaveResponse(this.caseReportService.update(caseReport));
    } else {
      this.subscribeToSaveResponse(this.caseReportService.create(caseReport));
    }
  }

  private createFromForm(): ICaseReport {
    return {
      ...new CaseReport(),
      id: this.editForm.get(['id']).value,
      date: this.editForm.get(['date']).value != null ? moment(this.editForm.get(['date']).value, DATE_TIME_FORMAT) : undefined,
      personDetails: this.editForm.get(['personDetails']).value,
      eventDescription: this.editForm.get(['eventDescription']).value,
      person: this.editForm.get(['person']).value,
      mugshotReport: this.editForm.get(['mugshotReport']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaseReport>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackPersonById(index: number, item: IPerson) {
    return item.id;
  }

  trackResourceURLById(index: number, item: IResourceURL) {
    return item.id;
  }
}
