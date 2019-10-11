import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';

@Component({
  selector: 'jhi-pre-incarceration-registry-detail',
  templateUrl: './pre-incarceration-registry-detail.component.html'
})
export class PreIncarcerationRegistryDetailComponent implements OnInit {
  preIncarcerationRegistry: IPreIncarcerationRegistry;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ preIncarcerationRegistry }) => {
      this.preIncarcerationRegistry = preIncarcerationRegistry;
    });
  }

  previousState() {
    window.history.back();
  }
}
