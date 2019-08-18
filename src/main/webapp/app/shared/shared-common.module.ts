import { NgModule } from '@angular/core';

import { CriminalddbbSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [CriminalddbbSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [CriminalddbbSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CriminalddbbSharedCommonModule {}
