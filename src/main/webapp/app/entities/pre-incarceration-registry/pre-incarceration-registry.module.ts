import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CriminalDdbbSharedModule } from 'app/shared';
import {
  PreIncarcerationRegistryComponent,
  PreIncarcerationRegistryDetailComponent,
  PreIncarcerationRegistryUpdateComponent,
  PreIncarcerationRegistryDeletePopupComponent,
  PreIncarcerationRegistryDeleteDialogComponent,
  preIncarcerationRegistryRoute,
  preIncarcerationRegistryPopupRoute
} from './';

const ENTITY_STATES = [...preIncarcerationRegistryRoute, ...preIncarcerationRegistryPopupRoute];

@NgModule({
  imports: [CriminalDdbbSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    PreIncarcerationRegistryComponent,
    PreIncarcerationRegistryDetailComponent,
    PreIncarcerationRegistryUpdateComponent,
    PreIncarcerationRegistryDeleteDialogComponent,
    PreIncarcerationRegistryDeletePopupComponent
  ],
  entryComponents: [
    PreIncarcerationRegistryComponent,
    PreIncarcerationRegistryUpdateComponent,
    PreIncarcerationRegistryDeleteDialogComponent,
    PreIncarcerationRegistryDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalDdbbPreIncarcerationRegistryModule {}
