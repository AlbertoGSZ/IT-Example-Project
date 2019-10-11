import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IResourceUrl } from 'app/shared/model/resource-url.model';
import { AccountService } from 'app/core';
import { ResourceUrlService } from './resource-url.service';

@Component({
  selector: 'jhi-resource-url',
  templateUrl: './resource-url.component.html'
})
export class ResourceUrlComponent implements OnInit, OnDestroy {
  resourceUrls: IResourceUrl[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected resourceUrlService: ResourceUrlService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.resourceUrlService
      .query()
      .pipe(
        filter((res: HttpResponse<IResourceUrl[]>) => res.ok),
        map((res: HttpResponse<IResourceUrl[]>) => res.body)
      )
      .subscribe(
        (res: IResourceUrl[]) => {
          this.resourceUrls = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInResourceUrls();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IResourceUrl) {
    return item.id;
  }

  registerChangeInResourceUrls() {
    this.eventSubscriber = this.eventManager.subscribe('resourceUrlListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
