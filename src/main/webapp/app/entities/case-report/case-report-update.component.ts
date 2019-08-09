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

@Component({
  selector: 'jhi-case-report-update',
  templateUrl: './case-report-update.component.html'
})
export class CaseReportUpdateComponent implements OnInit {
  isSaving: boolean;

  people: IPerson[];

  editForm = this.fb.group({
    id: [],
    date: [],
    mugshot: [],
    personDetails: [],
    eventDescription: [],
    evidencePhotos: [],
    person: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected caseReportService: CaseReportService,
    protected personService: PersonService,
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
  }

  updateForm(caseReport: ICaseReport) {
    this.editForm.patchValue({
      id: caseReport.id,
      date: caseReport.date != null ? caseReport.date.format(DATE_TIME_FORMAT) : null,
      mugshot: caseReport.mugshot,
      personDetails: caseReport.personDetails,
      eventDescription: caseReport.eventDescription,
      evidencePhotos: caseReport.evidencePhotos,
      person: caseReport.person
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
      mugshot: this.editForm.get(['mugshot']).value,
      personDetails: this.editForm.get(['personDetails']).value,
      eventDescription: this.editForm.get(['eventDescription']).value,
      evidencePhotos: this.editForm.get(['evidencePhotos']).value,
      person: this.editForm.get(['person']).value
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
}
