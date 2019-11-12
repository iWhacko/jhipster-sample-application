import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ISetInfo, SetInfo } from 'app/shared/model/set-info.model';
import { SetInfoService } from './set-info.service';
import { IMedewerker } from 'app/shared/model/medewerker.model';
import { MedewerkerService } from 'app/entities/medewerker/medewerker.service';

@Component({
  selector: 'jhi-set-info-update',
  templateUrl: './set-info-update.component.html'
})
export class SetInfoUpdateComponent implements OnInit {
  isSaving: boolean;

  medewerkers: IMedewerker[];

  editForm = this.fb.group({
    id: [],
    geslacht: [],
    naam: [],
    achternaam: [],
    postCode: [],
    huisNummer: [],
    straat: [],
    plaats: [],
    telefoon: [],
    email: [],
    contract: [],
    telefonisch: [],
    taal: [],
    verzendMethode: [],
    medewerkerId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected setInfoService: SetInfoService,
    protected medewerkerService: MedewerkerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ setInfo }) => {
      this.updateForm(setInfo);
    });
    this.medewerkerService.query({ filter: 'setinfo-is-null' }).subscribe(
      (res: HttpResponse<IMedewerker[]>) => {
        if (!this.editForm.get('medewerkerId').value) {
          this.medewerkers = res.body;
        } else {
          this.medewerkerService
            .find(this.editForm.get('medewerkerId').value)
            .subscribe(
              (subRes: HttpResponse<IMedewerker>) => (this.medewerkers = [subRes.body].concat(res.body)),
              (subRes: HttpErrorResponse) => this.onError(subRes.message)
            );
        }
      },
      (res: HttpErrorResponse) => this.onError(res.message)
    );
  }

  updateForm(setInfo: ISetInfo) {
    this.editForm.patchValue({
      id: setInfo.id,
      geslacht: setInfo.geslacht,
      naam: setInfo.naam,
      achternaam: setInfo.achternaam,
      postCode: setInfo.postCode,
      huisNummer: setInfo.huisNummer,
      straat: setInfo.straat,
      plaats: setInfo.plaats,
      telefoon: setInfo.telefoon,
      email: setInfo.email,
      contract: setInfo.contract,
      telefonisch: setInfo.telefonisch,
      taal: setInfo.taal,
      verzendMethode: setInfo.verzendMethode,
      medewerkerId: setInfo.medewerkerId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const setInfo = this.createFromForm();
    if (setInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.setInfoService.update(setInfo));
    } else {
      this.subscribeToSaveResponse(this.setInfoService.create(setInfo));
    }
  }

  private createFromForm(): ISetInfo {
    return {
      ...new SetInfo(),
      id: this.editForm.get(['id']).value,
      geslacht: this.editForm.get(['geslacht']).value,
      naam: this.editForm.get(['naam']).value,
      achternaam: this.editForm.get(['achternaam']).value,
      postCode: this.editForm.get(['postCode']).value,
      huisNummer: this.editForm.get(['huisNummer']).value,
      straat: this.editForm.get(['straat']).value,
      plaats: this.editForm.get(['plaats']).value,
      telefoon: this.editForm.get(['telefoon']).value,
      email: this.editForm.get(['email']).value,
      contract: this.editForm.get(['contract']).value,
      telefonisch: this.editForm.get(['telefonisch']).value,
      taal: this.editForm.get(['taal']).value,
      verzendMethode: this.editForm.get(['verzendMethode']).value,
      medewerkerId: this.editForm.get(['medewerkerId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISetInfo>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackMedewerkerById(index: number, item: IMedewerker) {
    return item.id;
  }
}
