import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'person',
        loadChildren: () => import('./person/person.module').then(m => m.CriminalDdbbPersonModule)
      },
      {
        path: 'case-report',
        loadChildren: () => import('./case-report/case-report.module').then(m => m.CriminalDdbbCaseReportModule)
      },
      {
        path: 'pre-incarceration-registry',
        loadChildren: () =>
          import('./pre-incarceration-registry/pre-incarceration-registry.module').then(m => m.CriminalDdbbPreIncarcerationRegistryModule)
      },
      {
        path: 'resource-url',
        loadChildren: () => import('./resource-url/resource-url.module').then(m => m.CriminalDdbbResourceUrlModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CriminalDdbbEntityModule {}
