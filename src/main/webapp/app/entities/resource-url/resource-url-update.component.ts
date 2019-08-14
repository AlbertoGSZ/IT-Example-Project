import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IResourceURL, ResourceURL } from 'app/shared/model/resource-url.model';
import { ResourceURLService } from './resource-url.service';
import { ICaseReport } from 'app/shared/model/case-report.model';
import { CaseReportService } from 'app/entities/case-report';

@Component({
  selector: 'jhi-resource-url-update',
  templateUrl: './resource-url-update.component.html'
})
export class ResourceURLUpdateComponent implements OnInit {
  isSaving: boolean;

  casereports: ICaseReport[];

  editForm = this.fb.group({
    id: [],
    url: [],
    caseReport: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected resourceURLService: ResourceURLService,
    protected caseReportService: CaseReportService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ resourceURL }) => {
      this.updateForm(resourceURL);
    });
    this.caseReportService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICaseReport[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICaseReport[]>) => response.body)
      )
      .subscribe((res: ICaseReport[]) => (this.casereports = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(resourceURL: IResourceURL) {
    this.editForm.patchValue({
      id: resourceURL.id,
      url: resourceURL.url,
      caseReport: resourceURL.caseReport
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const resourceURL = this.createFromForm();
    if (resourceURL.id !== undefined) {
      this.subscribeToSaveResponse(this.resourceURLService.update(resourceURL));
    } else {
      this.subscribeToSaveResponse(this.resourceURLService.create(resourceURL));
    }
  }

  private createFromForm(): IResourceURL {
    return {
      ...new ResourceURL(),
      id: this.editForm.get(['id']).value,
      url: this.editForm.get(['url']).value,
      caseReport: this.editForm.get(['caseReport']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResourceURL>>) {
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

  trackCaseReportById(index: number, item: ICaseReport) {
    return item.id;
  }
}
