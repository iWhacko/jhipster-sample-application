import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISetInfo } from 'app/shared/model/set-info.model';

@Component({
  selector: 'jhi-set-info-detail',
  templateUrl: './set-info-detail.component.html'
})
export class SetInfoDetailComponent implements OnInit {
  setInfo: ISetInfo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ setInfo }) => {
      this.setInfo = setInfo;
    });
  }

  previousState() {
    window.history.back();
  }
}
