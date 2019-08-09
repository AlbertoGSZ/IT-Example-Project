/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CriminalBbddTestModule } from '../../../test.module';
import { CaseReportDetailComponent } from 'app/entities/case-report/case-report-detail.component';
import { CaseReport } from 'app/shared/model/case-report.model';

describe('Component Tests', () => {
  describe('CaseReport Management Detail Component', () => {
    let comp: CaseReportDetailComponent;
    let fixture: ComponentFixture<CaseReportDetailComponent>;
    const route = ({ data: of({ caseReport: new CaseReport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalBbddTestModule],
        declarations: [CaseReportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CaseReportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CaseReportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.caseReport).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
