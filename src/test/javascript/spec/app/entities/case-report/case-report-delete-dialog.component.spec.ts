/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CriminalDdbbTestModule } from '../../../test.module';
import { CaseReportDeleteDialogComponent } from 'app/entities/case-report/case-report-delete-dialog.component';
import { CaseReportService } from 'app/entities/case-report/case-report.service';

describe('Component Tests', () => {
  describe('CaseReport Management Delete Component', () => {
    let comp: CaseReportDeleteDialogComponent;
    let fixture: ComponentFixture<CaseReportDeleteDialogComponent>;
    let service: CaseReportService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [CaseReportDeleteDialogComponent]
      })
        .overrideTemplate(CaseReportDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CaseReportDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaseReportService);
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
