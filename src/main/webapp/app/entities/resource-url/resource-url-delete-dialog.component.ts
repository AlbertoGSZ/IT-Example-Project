import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IResourceUrl } from 'app/shared/model/resource-url.model';
import { ResourceUrlService } from './resource-url.service';

@Component({
  selector: 'jhi-resource-url-delete-dialog',
  templateUrl: './resource-url-delete-dialog.component.html'
})
export class ResourceUrlDeleteDialogComponent {
  resourceUrl: IResourceUrl;

  constructor(
    protected resourceUrlService: ResourceUrlService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.resourceUrlService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'resourceUrlListModification',
        content: 'Deleted an resourceUrl'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-resource-url-delete-popup',
  template: ''
})
export class ResourceUrlDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ resourceUrl }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ResourceUrlDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.resourceUrl = resourceUrl;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/resource-url', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/resource-url', { outlets: { popup: null } }]);
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
