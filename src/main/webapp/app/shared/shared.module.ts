import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CriminalBbddSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [CriminalBbddSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [CriminalBbddSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalBbddSharedModule {
  static forRoot() {
    return {
      ngModule: CriminalBbddSharedModule
    };
  }
}
