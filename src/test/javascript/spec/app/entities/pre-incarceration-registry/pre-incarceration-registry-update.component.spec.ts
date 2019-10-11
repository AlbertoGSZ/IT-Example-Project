/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { CriminalDdbbTestModule } from '../../../test.module';
import { PreIncarcerationRegistryUpdateComponent } from 'app/entities/pre-incarceration-registry/pre-incarceration-registry-update.component';
import { PreIncarcerationRegistryService } from 'app/entities/pre-incarceration-registry/pre-incarceration-registry.service';
import { PreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';

describe('Component Tests', () => {
  describe('PreIncarcerationRegistry Management Update Component', () => {
    let comp: PreIncarcerationRegistryUpdateComponent;
    let fixture: ComponentFixture<PreIncarcerationRegistryUpdateComponent>;
    let service: PreIncarcerationRegistryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CriminalDdbbTestModule],
        declarations: [PreIncarcerationRegistryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PreIncarcerationRegistryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PreIncarcerationRegistryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PreIncarcerationRegistryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PreIncarcerationRegistry(123);
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
        const entity = new PreIncarcerationRegistry();
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
