import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';
import { AccountService } from 'app/core';
import { PreIncarcerationRegistryService } from './pre-incarceration-registry.service';

@Component({
  selector: 'jhi-pre-incarceration-registry',
  templateUrl: './pre-incarceration-registry.component.html'
})
export class PreIncarcerationRegistryComponent implements OnInit, OnDestroy {
  preIncarcerationRegistries: IPreIncarcerationRegistry[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected preIncarcerationRegistryService: PreIncarcerationRegistryService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.preIncarcerationRegistryService
      .query()
      .pipe(
        filter((res: HttpResponse<IPreIncarcerationRegistry[]>) => res.ok),
        map((res: HttpResponse<IPreIncarcerationRegistry[]>) => res.body)
      )
      .subscribe(
        (res: IPreIncarcerationRegistry[]) => {
          this.preIncarcerationRegistries = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInPreIncarcerationRegistries();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IPreIncarcerationRegistry) {
    return item.id;
  }

  registerChangeInPreIncarcerationRegistries() {
    this.eventSubscriber = this.eventManager.subscribe('preIncarcerationRegistryListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
