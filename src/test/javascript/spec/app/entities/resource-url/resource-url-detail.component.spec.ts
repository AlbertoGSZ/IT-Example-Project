/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CriminalDdbbTestModule } from '../../../test.module';
import { ResourceUrlDetailComponent } from 'app/entities/resource-url/resource-url-detail.component';
import { ResourceUrl } from 'app/shared/model/resource-url.model';

describe('Component Tests', () => {
  describe('ResourceUrl Management Detail Component', () => {
    let comp: ResourceUrlDetailComponent;
    let fixture: ComponentFixture<ResourceUrlDetailComponent>;
    const route = ({ data: of({ resourceUrl: new ResourceUrl(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [ResourceUrlDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ResourceUrlDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResourceUrlDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.resourceUrl).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
