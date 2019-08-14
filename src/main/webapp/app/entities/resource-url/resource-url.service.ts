import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResourceURL } from 'app/shared/model/resource-url.model';

type EntityResponseType = HttpResponse<IResourceURL>;
type EntityArrayResponseType = HttpResponse<IResourceURL[]>;

@Injectable({ providedIn: 'root' })
export class ResourceURLService {
  public resourceUrl = SERVER_API_URL + 'api/resource-urls';

  constructor(protected http: HttpClient) {}

  create(resourceURL: IResourceURL): Observable<EntityResponseType> {
    return this.http.post<IResourceURL>(this.resourceUrl, resourceURL, { observe: 'response' });
  }

  update(resourceURL: IResourceURL): Observable<EntityResponseType> {
    return this.http.put<IResourceURL>(this.resourceUrl, resourceURL, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IResourceURL>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResourceURL[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
