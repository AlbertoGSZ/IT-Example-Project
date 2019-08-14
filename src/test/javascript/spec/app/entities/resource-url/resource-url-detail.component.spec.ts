/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CriminalBbddTestModule } from '../../../test.module';
import { ResourceURLDetailComponent } from 'app/entities/resource-url/resource-url-detail.component';
import { ResourceURL } from 'app/shared/model/resource-url.model';

describe('Component Tests', () => {
  describe('ResourceURL Management Detail Component', () => {
    let comp: ResourceURLDetailComponent;
    let fixture: ComponentFixture<ResourceURLDetailComponent>;
    const route = ({ data: of({ resourceURL: new ResourceURL(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalBbddTestModule],
        declarations: [ResourceURLDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ResourceURLDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ResourceURLDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.resourceURL).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
