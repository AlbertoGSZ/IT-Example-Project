import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CriminalddbbSharedModule } from 'app/shared';
import {
  ResourceURLComponent,
  ResourceURLDetailComponent,
  ResourceURLUpdateComponent,
  ResourceURLDeletePopupComponent,
  ResourceURLDeleteDialogComponent,
  resourceURLRoute,
  resourceURLPopupRoute
} from './';

const ENTITY_STATES = [...resourceURLRoute, ...resourceURLPopupRoute];

@NgModule({
  imports: [CriminalddbbSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ResourceURLComponent,
    ResourceURLDetailComponent,
    ResourceURLUpdateComponent,
    ResourceURLDeleteDialogComponent,
    ResourceURLDeletePopupComponent
  ],
  entryComponents: [ResourceURLComponent, ResourceURLUpdateComponent, ResourceURLDeleteDialogComponent, ResourceURLDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalddbbResourceURLModule {}
