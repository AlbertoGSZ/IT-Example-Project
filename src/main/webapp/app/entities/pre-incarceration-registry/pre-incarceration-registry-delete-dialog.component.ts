import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';
import { PreIncarcerationRegistryService } from './pre-incarceration-registry.service';

@Component({
  selector: 'jhi-pre-incarceration-registry-delete-dialog',
  templateUrl: './pre-incarceration-registry-delete-dialog.component.html'
})
export class PreIncarcerationRegistryDeleteDialogComponent {
  preIncarcerationRegistry: IPreIncarcerationRegistry;

  constructor(
    protected preIncarcerationRegistryService: PreIncarcerationRegistryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.preIncarcerationRegistryService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'preIncarcerationRegistryListModification',
        content: 'Deleted an preIncarcerationRegistry'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-pre-incarceration-registry-delete-popup',
  template: ''
})
export class PreIncarcerationRegistryDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ preIncarcerationRegistry }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PreIncarcerationRegistryDeleteDialogComponent as Component, {
          size: 'lg',
          backdrop: 'static'
        });
        this.ngbModalRef.componentInstance.preIncarcerationRegistry = preIncarcerationRegistry;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/pre-incarceration-registry', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/pre-incarceration-registry', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
