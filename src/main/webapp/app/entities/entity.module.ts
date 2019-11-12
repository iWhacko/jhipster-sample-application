import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'medewerker',
        loadChildren: () => import('./medewerker/medewerker.module').then(m => m.JhipsterSampleApplicationMedewerkerModule)
      },
      {
        path: 'set-info',
        loadChildren: () => import('./set-info/set-info.module').then(m => m.JhipsterSampleApplicationSetInfoModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhipsterSampleApplicationEntityModule {}
