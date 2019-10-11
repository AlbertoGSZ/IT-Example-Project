/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { CriminalDdbbTestModule } from '../../../test.module';
import { ResourceUrlUpdateComponent } from 'app/entities/resource-url/resource-url-update.component';
import { ResourceUrlService } from 'app/entities/resource-url/resource-url.service';
import { ResourceUrl } from 'app/shared/model/resource-url.model';

describe('Component Tests', () => {
  describe('ResourceUrl Management Update Component', () => {
    let comp: ResourceUrlUpdateComponent;
    let fixture: ComponentFixture<ResourceUrlUpdateComponent>;
    let service: ResourceUrlService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [ResourceUrlUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ResourceUrlUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResourceUrlUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResourceUrlService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ResourceUrl(123);
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
        const entity = new ResourceUrl();
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
