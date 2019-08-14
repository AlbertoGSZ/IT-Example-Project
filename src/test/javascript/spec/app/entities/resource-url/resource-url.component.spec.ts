/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CriminalBbddTestModule } from '../../../test.module';
import { ResourceURLComponent } from 'app/entities/resource-url/resource-url.component';
import { ResourceURLService } from 'app/entities/resource-url/resource-url.service';
import { ResourceURL } from 'app/shared/model/resource-url.model';

describe('Component Tests', () => {
  describe('ResourceURL Management Component', () => {
    let comp: ResourceURLComponent;
    let fixture: ComponentFixture<ResourceURLComponent>;
    let service: ResourceURLService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalBbddTestModule],
        declarations: [ResourceURLComponent],
        providers: []
      })
        .overrideTemplate(ResourceURLComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResourceURLComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResourceURLService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ResourceURL(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.resourceURLS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
