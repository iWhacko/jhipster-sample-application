import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMedewerker } from 'app/shared/model/medewerker.model';

type EntityResponseType = HttpResponse<IMedewerker>;
type EntityArrayResponseType = HttpResponse<IMedewerker[]>;

@Injectable({ providedIn: 'root' })
export class MedewerkerService {
  public resourceUrl = SERVER_API_URL + 'api/medewerkers';

  constructor(protected http: HttpClient) {}

  create(medewerker: IMedewerker): Observable<EntityResponseType> {
    return this.http.post<IMedewerker>(this.resourceUrl, medewerker, { observe: 'response' });
  }

  update(medewerker: IMedewerker): Observable<EntityResponseType> {
    return this.http.put<IMedewerker>(this.resourceUrl, medewerker, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMedewerker>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMedewerker[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
