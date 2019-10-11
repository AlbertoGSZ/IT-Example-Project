import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IResourceUrl } from 'app/shared/model/resource-url.model';

@Component({
  selector: 'jhi-resource-url-detail',
  templateUrl: './resource-url-detail.component.html'
})
export class ResourceUrlDetailComponent implements OnInit {
  resourceUrl: IResourceUrl;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ resourceUrl }) => {
      this.resourceUrl = resourceUrl;
    });
  }

  previousState() {
    window.history.back();
  }
}
