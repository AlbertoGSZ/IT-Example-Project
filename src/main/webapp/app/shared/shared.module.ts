import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CriminalddbbSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [CriminalddbbSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [CriminalddbbSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalddbbSharedModule {
  static forRoot() {
    return {
      ngModule: CriminalddbbSharedModule
    };
  }
}
