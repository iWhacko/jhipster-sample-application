import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISetInfo } from 'app/shared/model/set-info.model';
import { SetInfoService } from './set-info.service';

@Component({
  selector: 'jhi-set-info-delete-dialog',
  templateUrl: './set-info-delete-dialog.component.html'
})
export class SetInfoDeleteDialogComponent {
  setInfo: ISetInfo;

  constructor(protected setInfoService: SetInfoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.setInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'setInfoListModification',
        content: 'Deleted an setInfo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-set-info-delete-popup',
  template: ''
})
export class SetInfoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ setInfo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(SetInfoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.setInfo = setInfo;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/set-info', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/set-info', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
