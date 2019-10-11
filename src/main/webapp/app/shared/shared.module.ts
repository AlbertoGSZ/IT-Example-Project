import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CriminalDdbbSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [CriminalDdbbSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [CriminalDdbbSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalDdbbSharedModule {
  static forRoot() {
    return {
      ngModule: CriminalDdbbSharedModule
    };
  }
}
