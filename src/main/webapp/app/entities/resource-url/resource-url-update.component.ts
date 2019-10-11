import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IResourceUrl, ResourceUrl } from 'app/shared/model/resource-url.model';
import { ResourceUrlService } from './resource-url.service';
import { ICaseReport } from 'app/shared/model/case-report.model';
import { CaseReportService } from 'app/entities/case-report';

@Component({
  selector: 'jhi-resource-url-update',
  templateUrl: './resource-url-update.component.html'
})
export class ResourceUrlUpdateComponent implements OnInit {
  isSaving: boolean;

  casereports: ICaseReport[];

  editForm = this.fb.group({
    id: [],
    urlLink: [],
    caseReport: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected resourceUrlService: ResourceUrlService,
    protected caseReportService: CaseReportService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ resourceUrl }) => {
      this.updateForm(resourceUrl);
    });
    this.caseReportService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICaseReport[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICaseReport[]>) => response.body)
      )
      .subscribe((res: ICaseReport[]) => (this.casereports = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(resourceUrl: IResourceUrl) {
    this.editForm.patchValue({
      id: resourceUrl.id,
      urlLink: resourceUrl.urlLink,
      caseReport: resourceUrl.caseReport
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const resourceUrl = this.createFromForm();
    if (resourceUrl.id !== undefined) {
      this.subscribeToSaveResponse(this.resourceUrlService.update(resourceUrl));
    } else {
      this.subscribeToSaveResponse(this.resourceUrlService.create(resourceUrl));
    }
  }

  private createFromForm(): IResourceUrl {
    return {
      ...new ResourceUrl(),
      id: this.editForm.get(['id']).value,
      urlLink: this.editForm.get(['urlLink']).value,
      caseReport: this.editForm.get(['caseReport']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResourceUrl>>) {
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
