/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CriminalDdbbTestModule } from '../../../test.module';
import { PreIncarcerationRegistryComponent } from 'app/entities/pre-incarceration-registry/pre-incarceration-registry.component';
import { PreIncarcerationRegistryService } from 'app/entities/pre-incarceration-registry/pre-incarceration-registry.service';
import { PreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';

describe('Component Tests', () => {
  describe('PreIncarcerationRegistry Management Component', () => {
    let comp: PreIncarcerationRegistryComponent;
    let fixture: ComponentFixture<PreIncarcerationRegistryComponent>;
    let service: PreIncarcerationRegistryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [PreIncarcerationRegistryComponent],
        providers: []
      })
        .overrideTemplate(PreIncarcerationRegistryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PreIncarcerationRegistryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PreIncarcerationRegistryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PreIncarcerationRegistry(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.preIncarcerationRegistries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
