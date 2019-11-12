import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { SetInfoService } from 'app/entities/set-info/set-info.service';
import { ISetInfo, SetInfo } from 'app/shared/model/set-info.model';

describe('Service Tests', () => {
  describe('SetInfo Service', () => {
    let injector: TestBed;
    let service: SetInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: ISetInfo;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(SetInfoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SetInfo(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a SetInfo', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new SetInfo(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a SetInfo', () => {
        const returnedFromService = Object.assign(
          {
            geslacht: 'BBBBBB',
            naam: 'BBBBBB',
            achternaam: 'BBBBBB',
            postCode: 'BBBBBB',
            huisNummer: 'BBBBBB',
            straat: 'BBBBBB',
            plaats: 'BBBBBB',
            telefoon: 'BBBBBB',
            email: 'BBBBBB',
            contract: 'BBBBBB',
            telefonisch: true,
            taal: 'BBBBBB',
            verzendMethode: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of SetInfo', () => {
        const returnedFromService = Object.assign(
          {
            geslacht: 'BBBBBB',
            naam: 'BBBBBB',
            achternaam: 'BBBBBB',
            postCode: 'BBBBBB',
            huisNummer: 'BBBBBB',
            straat: 'BBBBBB',
            plaats: 'BBBBBB',
            telefoon: 'BBBBBB',
            email: 'BBBBBB',
            contract: 'BBBBBB',
            telefonisch: true,
            taal: 'BBBBBB',
            verzendMethode: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SetInfo', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
