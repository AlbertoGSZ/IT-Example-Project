import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { CriminalBbddSharedModule } from 'app/shared';
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
  imports: [CriminalBbddSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ResourceURLComponent,
    ResourceURLDetailComponent,
    ResourceURLUpdateComponent,
    ResourceURLDeleteDialogComponent,
    ResourceURLDeletePopupComponent
  ],
  entryComponents: [ResourceURLComponent, ResourceURLUpdateComponent, ResourceURLDeleteDialogComponent, ResourceURLDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalBbddResourceURLModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
