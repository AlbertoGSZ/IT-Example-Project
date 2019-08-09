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

@Component({
  selector: 'jhi-person-update',
  templateUrl: './person-update.component.html'
})
export class PersonUpdateComponent implements OnInit {
  isSaving: boolean;

  people: IPerson[];
  birthDateDp: any;

  editForm = this.fb.group({
    id: [],
    name: [],
    surname: [],
    birthDate: [],
    age: [],
    adress: [],
    nationality: [],
    sex: [],
    alias: [],
    rank: [],
    chief: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected personService: PersonService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ person }) => {
      this.updateForm(person);
    });
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
      birthDate: person.birthDate,
      age: person.age != null ? person.age.format(DATE_TIME_FORMAT) : null,
      adress: person.adress,
      nationality: person.nationality,
      sex: person.sex,
      alias: person.alias,
      rank: person.rank,
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
      birthDate: this.editForm.get(['birthDate']).value,
      age: this.editForm.get(['age']).value != null ? moment(this.editForm.get(['age']).value, DATE_TIME_FORMAT) : undefined,
      adress: this.editForm.get(['adress']).value,
      nationality: this.editForm.get(['nationality']).value,
      sex: this.editForm.get(['sex']).value,
      alias: this.editForm.get(['alias']).value,
      rank: this.editForm.get(['rank']).value,
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

  trackPersonById(index: number, item: IPerson) {
    return item.id;
  }
}
