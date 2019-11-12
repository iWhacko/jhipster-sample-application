import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { MedewerkerComponent } from './medewerker.component';
import { MedewerkerDetailComponent } from './medewerker-detail.component';
import { MedewerkerUpdateComponent } from './medewerker-update.component';
import { MedewerkerDeletePopupComponent, MedewerkerDeleteDialogComponent } from './medewerker-delete-dialog.component';
import { medewerkerRoute, medewerkerPopupRoute } from './medewerker.route';

const ENTITY_STATES = [...medewerkerRoute, ...medewerkerPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MedewerkerComponent,
    MedewerkerDetailComponent,
    MedewerkerUpdateComponent,
    MedewerkerDeleteDialogComponent,
    MedewerkerDeletePopupComponent
  ],
  entryComponents: [MedewerkerDeleteDialogComponent]
})
export class JhipsterSampleApplicationMedewerkerModule {}
