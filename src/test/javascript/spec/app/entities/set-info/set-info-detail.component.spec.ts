import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SetInfoDetailComponent } from 'app/entities/set-info/set-info-detail.component';
import { SetInfo } from 'app/shared/model/set-info.model';

describe('Component Tests', () => {
  describe('SetInfo Management Detail Component', () => {
    let comp: SetInfoDetailComponent;
    let fixture: ComponentFixture<SetInfoDetailComponent>;
    const route = ({ data: of({ setInfo: new SetInfo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterSampleApplicationTestModule],
        declarations: [SetInfoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SetInfoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SetInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.setInfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
