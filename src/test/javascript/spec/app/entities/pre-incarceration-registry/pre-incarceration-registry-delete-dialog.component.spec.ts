/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CriminalDdbbTestModule } from '../../../test.module';
import { PreIncarcerationRegistryDeleteDialogComponent } from 'app/entities/pre-incarceration-registry/pre-incarceration-registry-delete-dialog.component';
import { PreIncarcerationRegistryService } from 'app/entities/pre-incarceration-registry/pre-incarceration-registry.service';

describe('Component Tests', () => {
  describe('PreIncarcerationRegistry Management Delete Component', () => {
    let comp: PreIncarcerationRegistryDeleteDialogComponent;
    let fixture: ComponentFixture<PreIncarcerationRegistryDeleteDialogComponent>;
    let service: PreIncarcerationRegistryService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [PreIncarcerationRegistryDeleteDialogComponent]
      })
        .overrideTemplate(PreIncarcerationRegistryDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PreIncarcerationRegistryDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PreIncarcerationRegistryService);
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
