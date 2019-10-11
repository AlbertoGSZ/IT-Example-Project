/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { CriminalDdbbTestModule } from '../../../test.module';
import { CaseReportUpdateComponent } from 'app/entities/case-report/case-report-update.component';
import { CaseReportService } from 'app/entities/case-report/case-report.service';
import { CaseReport } from 'app/shared/model/case-report.model';

describe('Component Tests', () => {
  describe('CaseReport Management Update Component', () => {
    let comp: CaseReportUpdateComponent;
    let fixture: ComponentFixture<CaseReportUpdateComponent>;
    let service: CaseReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [CaseReportUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CaseReportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaseReportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaseReportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CaseReport(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CaseReport();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
