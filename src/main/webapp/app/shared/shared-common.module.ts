import { NgModule } from '@angular/core';

import { CriminalDdbbSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [CriminalDdbbSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [CriminalDdbbSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CriminalDdbbSharedCommonModule {}
