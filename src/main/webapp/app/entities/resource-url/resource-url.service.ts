import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IResourceUrl } from 'app/shared/model/resource-url.model';

type EntityResponseType = HttpResponse<IResourceUrl>;
type EntityArrayResponseType = HttpResponse<IResourceUrl[]>;

@Injectable({ providedIn: 'root' })
export class ResourceUrlService {
  public resourceUrl = SERVER_API_URL + 'api/resource-urls';

  constructor(protected http: HttpClient) {}

  create(resourceUrl: IResourceUrl): Observable<EntityResponseType> {
    return this.http.post<IResourceUrl>(this.resourceUrl, resourceUrl, { observe: 'response' });
  }

  update(resourceUrl: IResourceUrl): Observable<EntityResponseType> {
    return this.http.put<IResourceUrl>(this.resourceUrl, resourceUrl, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IResourceUrl>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IResourceUrl[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
