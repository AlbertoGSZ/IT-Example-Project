/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CriminalDdbbTestModule } from '../../../test.module';
import { ResourceUrlDeleteDialogComponent } from 'app/entities/resource-url/resource-url-delete-dialog.component';
import { ResourceUrlService } from 'app/entities/resource-url/resource-url.service';

describe('Component Tests', () => {
  describe('ResourceUrl Management Delete Component', () => {
    let comp: ResourceUrlDeleteDialogComponent;
    let fixture: ComponentFixture<ResourceUrlDeleteDialogComponent>;
    let service: ResourceUrlService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [ResourceUrlDeleteDialogComponent]
      })
        .overrideTemplate(ResourceUrlDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResourceUrlDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResourceUrlService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
