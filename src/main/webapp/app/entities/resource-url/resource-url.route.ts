import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ResourceURL } from 'app/shared/model/resource-url.model';
import { ResourceURLService } from './resource-url.service';
import { ResourceURLComponent } from './resource-url.component';
import { ResourceURLDetailComponent } from './resource-url-detail.component';
import { ResourceURLUpdateComponent } from './resource-url-update.component';
import { ResourceURLDeletePopupComponent } from './resource-url-delete-dialog.component';
import { IResourceURL } from 'app/shared/model/resource-url.model';

@Injectable({ providedIn: 'root' })
export class ResourceURLResolve implements Resolve<IResourceURL> {
  constructor(private service: ResourceURLService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IResourceURL> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ResourceURL>) => response.ok),
        map((resourceURL: HttpResponse<ResourceURL>) => resourceURL.body)
      );
    }
    return of(new ResourceURL());
  }
}

export const resourceURLRoute: Routes = [
  {
    path: '',
    component: ResourceURLComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceURLS'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ResourceURLDetailComponent,
    resolve: {
      resourceURL: ResourceURLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceURLS'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ResourceURLUpdateComponent,
    resolve: {
      resourceURL: ResourceURLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceURLS'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ResourceURLUpdateComponent,
    resolve: {
      resourceURL: ResourceURLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceURLS'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const resourceURLPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ResourceURLDeletePopupComponent,
    resolve: {
      resourceURL: ResourceURLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceURLS'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
