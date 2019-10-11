import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IPreIncarcerationRegistry, PreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';
import { PreIncarcerationRegistryService } from './pre-incarceration-registry.service';

@Component({
  selector: 'jhi-pre-incarceration-registry-update',
  templateUrl: './pre-incarceration-registry-update.component.html'
})
export class PreIncarcerationRegistryUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    oldRank: [],
    oldChief: [],
    oldSubordinates: []
  });

  constructor(
    protected preIncarcerationRegistryService: PreIncarcerationRegistryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ preIncarcerationRegistry }) => {
      this.updateForm(preIncarcerationRegistry);
    });
  }

  updateForm(preIncarcerationRegistry: IPreIncarcerationRegistry) {
    this.editForm.patchValue({
      id: preIncarcerationRegistry.id,
      oldRank: preIncarcerationRegistry.oldRank,
      oldChief: preIncarcerationRegistry.oldChief,
      oldSubordinates: preIncarcerationRegistry.oldSubordinates
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const preIncarcerationRegistry = this.createFromForm();
    if (preIncarcerationRegistry.id !== undefined) {
      this.subscribeToSaveResponse(this.preIncarcerationRegistryService.update(preIncarcerationRegistry));
    } else {
      this.subscribeToSaveResponse(this.preIncarcerationRegistryService.create(preIncarcerationRegistry));
    }
  }

  private createFromForm(): IPreIncarcerationRegistry {
    return {
      ...new PreIncarcerationRegistry(),
      id: this.editForm.get(['id']).value,
      oldRank: this.editForm.get(['oldRank']).value,
      oldChief: this.editForm.get(['oldChief']).value,
      oldSubordinates: this.editForm.get(['oldSubordinates']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPreIncarcerationRegistry>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
