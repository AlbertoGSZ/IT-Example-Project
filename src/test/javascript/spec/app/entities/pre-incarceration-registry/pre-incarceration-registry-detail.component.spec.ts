/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CriminalDdbbTestModule } from '../../../test.module';
import { PreIncarcerationRegistryDetailComponent } from 'app/entities/pre-incarceration-registry/pre-incarceration-registry-detail.component';
import { PreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';

describe('Component Tests', () => {
  describe('PreIncarcerationRegistry Management Detail Component', () => {
    let comp: PreIncarcerationRegistryDetailComponent;
    let fixture: ComponentFixture<PreIncarcerationRegistryDetailComponent>;
    const route = ({ data: of({ preIncarcerationRegistry: new PreIncarcerationRegistry(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [PreIncarcerationRegistryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PreIncarcerationRegistryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PreIncarcerationRegistryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.preIncarcerationRegistry).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
