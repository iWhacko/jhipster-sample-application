import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Medewerker } from 'app/shared/model/medewerker.model';
import { MedewerkerService } from './medewerker.service';
import { MedewerkerComponent } from './medewerker.component';
import { MedewerkerDetailComponent } from './medewerker-detail.component';
import { MedewerkerUpdateComponent } from './medewerker-update.component';
import { MedewerkerDeletePopupComponent } from './medewerker-delete-dialog.component';
import { IMedewerker } from 'app/shared/model/medewerker.model';

@Injectable({ providedIn: 'root' })
export class MedewerkerResolve implements Resolve<IMedewerker> {
  constructor(private service: MedewerkerService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMedewerker> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((medewerker: HttpResponse<Medewerker>) => medewerker.body));
    }
    return of(new Medewerker());
  }
}

export const medewerkerRoute: Routes = [
  {
    path: '',
    component: MedewerkerComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'Medewerkers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MedewerkerDetailComponent,
    resolve: {
      medewerker: MedewerkerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Medewerkers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MedewerkerUpdateComponent,
    resolve: {
      medewerker: MedewerkerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Medewerkers'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MedewerkerUpdateComponent,
    resolve: {
      medewerker: MedewerkerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Medewerkers'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const medewerkerPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MedewerkerDeletePopupComponent,
    resolve: {
      medewerker: MedewerkerResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'Medewerkers'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
