/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { CriminalddbbTestModule } from '../../../test.module';
import { ResourceURLUpdateComponent } from 'app/entities/resource-url/resource-url-update.component';
import { ResourceURLService } from 'app/entities/resource-url/resource-url.service';
import { ResourceURL } from 'app/shared/model/resource-url.model';

describe('Component Tests', () => {
  describe('ResourceURL Management Update Component', () => {
    let comp: ResourceURLUpdateComponent;
    let fixture: ComponentFixture<ResourceURLUpdateComponent>;
    let service: ResourceURLService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalddbbTestModule],
        declarations: [ResourceURLUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ResourceURLUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResourceURLUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResourceURLService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ResourceURL(123);
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
        const entity = new ResourceURL();
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
