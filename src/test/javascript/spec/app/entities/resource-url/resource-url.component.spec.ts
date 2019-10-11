/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CriminalDdbbTestModule } from '../../../test.module';
import { ResourceUrlComponent } from 'app/entities/resource-url/resource-url.component';
import { ResourceUrlService } from 'app/entities/resource-url/resource-url.service';
import { ResourceUrl } from 'app/shared/model/resource-url.model';

describe('Component Tests', () => {
  describe('ResourceUrl Management Component', () => {
    let comp: ResourceUrlComponent;
    let fixture: ComponentFixture<ResourceUrlComponent>;
    let service: ResourceUrlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [ResourceUrlComponent],
        providers: []
      })
        .overrideTemplate(ResourceUrlComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResourceUrlComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResourceUrlService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ResourceUrl(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.resourceUrls[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
