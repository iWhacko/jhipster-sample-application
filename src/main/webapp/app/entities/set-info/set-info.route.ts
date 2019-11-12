import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SetInfo } from 'app/shared/model/set-info.model';
import { SetInfoService } from './set-info.service';
import { SetInfoComponent } from './set-info.component';
import { SetInfoDetailComponent } from './set-info-detail.component';
import { SetInfoUpdateComponent } from './set-info-update.component';
import { SetInfoDeletePopupComponent } from './set-info-delete-dialog.component';
import { ISetInfo } from 'app/shared/model/set-info.model';

@Injectable({ providedIn: 'root' })
export class SetInfoResolve implements Resolve<ISetInfo> {
  constructor(private service: SetInfoService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISetInfo> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((setInfo: HttpResponse<SetInfo>) => setInfo.body));
    }
    return of(new SetInfo());
  }
}

export const setInfoRoute: Routes = [
  {
    path: '',
    component: SetInfoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'SetInfos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SetInfoDetailComponent,
    resolve: {
      setInfo: SetInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SetInfos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SetInfoUpdateComponent,
    resolve: {
      setInfo: SetInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SetInfos'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SetInfoUpdateComponent,
    resolve: {
      setInfo: SetInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SetInfos'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const setInfoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SetInfoDeletePopupComponent,
    resolve: {
      setInfo: SetInfoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'SetInfos'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
