import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IMedewerker, Medewerker } from 'app/shared/model/medewerker.model';
import { MedewerkerService } from './medewerker.service';

@Component({
  selector: 'jhi-medewerker-update',
  templateUrl: './medewerker-update.component.html'
})
export class MedewerkerUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    naam: [],
    achterNaam: []
  });

  constructor(protected medewerkerService: MedewerkerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ medewerker }) => {
      this.updateForm(medewerker);
    });
  }

  updateForm(medewerker: IMedewerker) {
    this.editForm.patchValue({
      id: medewerker.id,
      naam: medewerker.naam,
      achterNaam: medewerker.achterNaam
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const medewerker = this.createFromForm();
    if (medewerker.id !== undefined) {
      this.subscribeToSaveResponse(this.medewerkerService.update(medewerker));
    } else {
      this.subscribeToSaveResponse(this.medewerkerService.create(medewerker));
    }
  }

  private createFromForm(): IMedewerker {
    return {
      ...new Medewerker(),
      id: this.editForm.get(['id']).value,
      naam: this.editForm.get(['naam']).value,
      achterNaam: this.editForm.get(['achterNaam']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedewerker>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
