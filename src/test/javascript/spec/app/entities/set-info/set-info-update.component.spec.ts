import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SetInfoUpdateComponent } from 'app/entities/set-info/set-info-update.component';
import { SetInfoService } from 'app/entities/set-info/set-info.service';
import { SetInfo } from 'app/shared/model/set-info.model';

describe('Component Tests', () => {
  describe('SetInfo Management Update Component', () => {
    let comp: SetInfoUpdateComponent;
    let fixture: ComponentFixture<SetInfoUpdateComponent>;
    let service: SetInfoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [SetInfoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SetInfoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SetInfoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SetInfoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SetInfo(123);
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
        const entity = new SetInfo();
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
