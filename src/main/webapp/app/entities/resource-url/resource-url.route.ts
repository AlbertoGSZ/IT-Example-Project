import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ResourceUrl } from 'app/shared/model/resource-url.model';
import { ResourceUrlService } from './resource-url.service';
import { ResourceUrlComponent } from './resource-url.component';
import { ResourceUrlDetailComponent } from './resource-url-detail.component';
import { ResourceUrlUpdateComponent } from './resource-url-update.component';
import { ResourceUrlDeletePopupComponent } from './resource-url-delete-dialog.component';
import { IResourceUrl } from 'app/shared/model/resource-url.model';

@Injectable({ providedIn: 'root' })
export class ResourceUrlResolve implements Resolve<IResourceUrl> {
  constructor(private service: ResourceUrlService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IResourceUrl> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ResourceUrl>) => response.ok),
        map((resourceUrl: HttpResponse<ResourceUrl>) => resourceUrl.body)
      );
    }
    return of(new ResourceUrl());
  }
}

export const resourceUrlRoute: Routes = [
  {
    path: '',
    component: ResourceUrlComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceUrls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ResourceUrlDetailComponent,
    resolve: {
      resourceUrl: ResourceUrlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceUrls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ResourceUrlUpdateComponent,
    resolve: {
      resourceUrl: ResourceUrlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceUrls'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ResourceUrlUpdateComponent,
    resolve: {
      resourceUrl: ResourceUrlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceUrls'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const resourceUrlPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ResourceUrlDeletePopupComponent,
    resolve: {
      resourceUrl: ResourceUrlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ResourceUrls'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
