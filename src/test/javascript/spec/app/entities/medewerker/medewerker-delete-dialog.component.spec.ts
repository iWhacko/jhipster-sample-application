import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MedewerkerDeleteDialogComponent } from 'app/entities/medewerker/medewerker-delete-dialog.component';
import { MedewerkerService } from 'app/entities/medewerker/medewerker.service';

describe('Component Tests', () => {
  describe('Medewerker Management Delete Component', () => {
    let comp: MedewerkerDeleteDialogComponent;
    let fixture: ComponentFixture<MedewerkerDeleteDialogComponent>;
    let service: MedewerkerService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [MedewerkerDeleteDialogComponent]
      })
        .overrideTemplate(MedewerkerDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedewerkerDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MedewerkerService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
