import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';
import { PreIncarcerationRegistryService } from './pre-incarceration-registry.service';
import { PreIncarcerationRegistryComponent } from './pre-incarceration-registry.component';
import { PreIncarcerationRegistryDetailComponent } from './pre-incarceration-registry-detail.component';
import { PreIncarcerationRegistryUpdateComponent } from './pre-incarceration-registry-update.component';
import { PreIncarcerationRegistryDeletePopupComponent } from './pre-incarceration-registry-delete-dialog.component';
import { IPreIncarcerationRegistry } from 'app/shared/model/pre-incarceration-registry.model';

@Injectable({ providedIn: 'root' })
export class PreIncarcerationRegistryResolve implements Resolve<IPreIncarcerationRegistry> {
  constructor(private service: PreIncarcerationRegistryService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPreIncarcerationRegistry> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PreIncarcerationRegistry>) => response.ok),
        map((preIncarcerationRegistry: HttpResponse<PreIncarcerationRegistry>) => preIncarcerationRegistry.body)
      );
    }
    return of(new PreIncarcerationRegistry());
  }
}

export const preIncarcerationRegistryRoute: Routes = [
  {
    path: '',
    component: PreIncarcerationRegistryComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PreIncarcerationRegistries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PreIncarcerationRegistryDetailComponent,
    resolve: {
      preIncarcerationRegistry: PreIncarcerationRegistryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PreIncarcerationRegistries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PreIncarcerationRegistryUpdateComponent,
    resolve: {
      preIncarcerationRegistry: PreIncarcerationRegistryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PreIncarcerationRegistries'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PreIncarcerationRegistryUpdateComponent,
    resolve: {
      preIncarcerationRegistry: PreIncarcerationRegistryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PreIncarcerationRegistries'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const preIncarcerationRegistryPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PreIncarcerationRegistryDeletePopupComponent,
    resolve: {
      preIncarcerationRegistry: PreIncarcerationRegistryResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PreIncarcerationRegistries'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
