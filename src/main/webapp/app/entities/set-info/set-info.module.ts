import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared/shared.module';
import { SetInfoComponent } from './set-info.component';
import { SetInfoDetailComponent } from './set-info-detail.component';
import { SetInfoUpdateComponent } from './set-info-update.component';
import { SetInfoDeletePopupComponent, SetInfoDeleteDialogComponent } from './set-info-delete-dialog.component';
import { setInfoRoute, setInfoPopupRoute } from './set-info.route';

const ENTITY_STATES = [...setInfoRoute, ...setInfoPopupRoute];

@NgModule({
  imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SetInfoComponent,
    SetInfoDetailComponent,
    SetInfoUpdateComponent,
    SetInfoDeleteDialogComponent,
    SetInfoDeletePopupComponent
  ],
  entryComponents: [SetInfoDeleteDialogComponent]
})
export class JhipsterSampleApplicationSetInfoModule {}
