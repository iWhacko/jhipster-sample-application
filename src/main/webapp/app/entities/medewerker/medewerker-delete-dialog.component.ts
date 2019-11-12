import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMedewerker } from 'app/shared/model/medewerker.model';
import { MedewerkerService } from './medewerker.service';

@Component({
  selector: 'jhi-medewerker-delete-dialog',
  templateUrl: './medewerker-delete-dialog.component.html'
})
export class MedewerkerDeleteDialogComponent {
  medewerker: IMedewerker;

  constructor(
    protected medewerkerService: MedewerkerService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.medewerkerService.delete(id).subscribe(() => {
      this.eventManager.broadcast({
        name: 'medewerkerListModification',
        content: 'Deleted an medewerker'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-medewerker-delete-popup',
  template: ''
})
export class MedewerkerDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ medewerker }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MedewerkerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.medewerker = medewerker;
        this.ngbModalRef.result.then(
          () => {
            this.router.navigate(['/medewerker', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          () => {
            this.router.navigate(['/medewerker', { outlets: { popup: null } }]);
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
