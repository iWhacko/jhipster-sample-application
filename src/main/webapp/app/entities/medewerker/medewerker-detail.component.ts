import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedewerker } from 'app/shared/model/medewerker.model';

@Component({
  selector: 'jhi-medewerker-detail',
  templateUrl: './medewerker-detail.component.html'
})
export class MedewerkerDetailComponent implements OnInit {
  medewerker: IMedewerker;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ medewerker }) => {
      this.medewerker = medewerker;
    });
  }

  previousState() {
    window.history.back();
  }
}
