import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IResourceURL } from 'app/shared/model/resource-url.model';
import { AccountService } from 'app/core';
import { ResourceURLService } from './resource-url.service';

@Component({
  selector: 'jhi-resource-url',
  templateUrl: './resource-url.component.html'
})
export class ResourceURLComponent implements OnInit, OnDestroy {
  resourceURLS: IResourceURL[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected resourceURLService: ResourceURLService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.resourceURLService
      .query()
      .pipe(
        filter((res: HttpResponse<IResourceURL[]>) => res.ok),
        map((res: HttpResponse<IResourceURL[]>) => res.body)
      )
      .subscribe(
        (res: IResourceURL[]) => {
          this.resourceURLS = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInResourceURLS();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IResourceURL) {
    return item.id;
  }

  registerChangeInResourceURLS() {
    this.eventSubscriber = this.eventManager.subscribe('resourceURLListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
