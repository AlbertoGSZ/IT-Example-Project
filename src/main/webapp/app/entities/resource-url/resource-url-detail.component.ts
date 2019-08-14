import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceURL } from 'app/shared/model/resource-url.model';

@Component({
  selector: 'jhi-resource-url-detail',
  templateUrl: './resource-url-detail.component.html'
})
export class ResourceURLDetailComponent implements OnInit {
  resourceURL: IResourceURL;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ resourceURL }) => {
      this.resourceURL = resourceURL;
    });
  }

  previousState() {
    window.history.back();
  }
}
