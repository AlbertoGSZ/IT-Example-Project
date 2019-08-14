/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CriminalBbddTestModule } from '../../../test.module';
import { ResourceURLDeleteDialogComponent } from 'app/entities/resource-url/resource-url-delete-dialog.component';
import { ResourceURLService } from 'app/entities/resource-url/resource-url.service';

describe('Component Tests', () => {
  describe('ResourceURL Management Delete Component', () => {
    let comp: ResourceURLDeleteDialogComponent;
    let fixture: ComponentFixture<ResourceURLDeleteDialogComponent>;
    let service: ResourceURLService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalBbddTestModule],
        declarations: [ResourceURLDeleteDialogComponent]
      })
        .overrideTemplate(ResourceURLDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResourceURLDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResourceURLService);
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