import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IPerson, Person } from 'app/shared/model/person.model';
import { PersonService } from './person.service';
import { IPreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';
import { PreIncarcerationRegistryService } from 'app/entities/pre-incarceration-registry';

@Component({
  selector: 'jhi-person-update',
  templateUrl: './person-update.component.html'
})
export class PersonUpdateComponent implements OnInit {
  isSaving: boolean;

  preincarcerationregistries: IPreIncarcerationRegistry[];

  people: IPerson[];

  editForm = this.fb.group({
    id: [],
    name: [],
    surname: [],
    birthDate: [],
    address: [],
    age: [],
    nationality: [],
    status: [],
    sex: [],
    alias: [],
    rank: [],
    preIncarcerationRegistry: [],
    chief: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected personService: PersonService,
    protected preIncarcerationRegistryService: PreIncarcerationRegistryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ person }) => {
      this.updateForm(person);
    });
    this.preIncarcerationRegistryService
      .query({ filter: 'person-is-null' })
      .pipe(
        filter((mayBeOk: HttpResponse<IPreIncarcerationRegistry[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPreIncarcerationRegistry[]>) => response.body)
      )
      .subscribe(
        (res: IPreIncarcerationRegistry[]) => {
          if (!this.editForm.get('preIncarcerationRegistry').value || !this.editForm.get('preIncarcerationRegistry').value.id) {
            this.preincarcerationregistries = res;
          } else {
            this.preIncarcerationRegistryService
              .find(this.editForm.get('preIncarcerationRegistry').value.id)
              .pipe(
                filter((subResMayBeOk: HttpResponse<IPreIncarcerationRegistry>) => subResMayBeOk.ok),
                map((subResponse: HttpResponse<IPreIncarcerationRegistry>) => subResponse.body)
              )
              .subscribe(
                (subRes: IPreIncarcerationRegistry) => (this.preincarcerationregistries = [subRes].concat(res)),
                (subRes: HttpErrorResponse) => this.onError(subRes.message)
              );
          }
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.personService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IPerson[]>) => mayBeOk.ok),
        map((response: HttpResponse<IPerson[]>) => response.body)
      )
      .subscribe((res: IPerson[]) => (this.people = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(person: IPerson) {
    this.editForm.patchValue({
      id: person.id,
      name: person.name,
      surname: person.surname,
      birthDate: person.birthDate != null ? person.birthDate.format(DATE_TIME_FORMAT) : null,
      address: person.address,
      age: person.age,
      nationality: person.nationality,
      status: person.status,
      sex: person.sex,
      alias: person.alias,
      rank: person.rank,
      preIncarcerationRegistry: person.preIncarcerationRegistry,
      chief: person.chief
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const person = this.createFromForm();
    if (person.id !== undefined) {
      this.subscribeToSaveResponse(this.personService.update(person));
    } else {
      this.subscribeToSaveResponse(this.personService.create(person));
    }
  }

  private createFromForm(): IPerson {
    return {
      ...new Person(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      surname: this.editForm.get(['surname']).value,
      birthDate:
        this.editForm.get(['birthDate']).value != null ? moment(this.editForm.get(['birthDate']).value, DATE_TIME_FORMAT) : undefined,
      address: this.editForm.get(['address']).value,
      age: this.editForm.get(['age']).value,
      nationality: this.editForm.get(['nationality']).value,
      status: this.editForm.get(['status']).value,
      sex: this.editForm.get(['sex']).value,
      alias: this.editForm.get(['alias']).value,
      rank: this.editForm.get(['rank']).value,
      preIncarcerationRegistry: this.editForm.get(['preIncarcerationRegistry']).value,
      chief: this.editForm.get(['chief']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerson>>) {
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

  trackPreIncarcerationRegistryById(index: number, item: IPreIncarcerationRegistry) {
    return item.id;
  }

  trackPersonById(index: number, item: IPerson) {
    return item.id;
  }
}
