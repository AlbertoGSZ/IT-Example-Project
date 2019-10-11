import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';

type EntityResponseType = HttpResponse<IPreIncarcerationRegistry>;
type EntityArrayResponseType = HttpResponse<IPreIncarcerationRegistry[]>;

@Injectable({ providedIn: 'root' })
export class PreIncarcerationRegistryService {
  public resourceUrl = SERVER_API_URL + 'api/pre-incarceration-registries';

  constructor(protected http: HttpClient) {}

  create(preIncarcerationRegistry: IPreIncarcerationRegistry): Observable<EntityResponseType> {
    return this.http.post<IPreIncarcerationRegistry>(this.resourceUrl, preIncarcerationRegistry, { observe: 'response' });
  }

  update(preIncarcerationRegistry: IPreIncarcerationRegistry): Observable<EntityResponseType> {
    return this.http.put<IPreIncarcerationRegistry>(this.resourceUrl, preIncarcerationRegistry, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPreIncarcerationRegistry>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPreIncarcerationRegistry[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
