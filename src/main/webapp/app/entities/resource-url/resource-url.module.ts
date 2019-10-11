import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CriminalDdbbSharedModule } from 'app/shared';
import {
  ResourceUrlComponent,
  ResourceUrlDetailComponent,
  ResourceUrlUpdateComponent,
  ResourceUrlDeletePopupComponent,
  ResourceUrlDeleteDialogComponent,
  resourceUrlRoute,
  resourceUrlPopupRoute
} from './';

const ENTITY_STATES = [...resourceUrlRoute, ...resourceUrlPopupRoute];

@NgModule({
  imports: [CriminalDdbbSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ResourceUrlComponent,
    ResourceUrlDetailComponent,
    ResourceUrlUpdateComponent,
    ResourceUrlDeleteDialogComponent,
    ResourceUrlDeletePopupComponent
  ],
  entryComponents: [ResourceUrlComponent, ResourceUrlUpdateComponent, ResourceUrlDeleteDialogComponent, ResourceUrlDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalDdbbResourceUrlModule {}
