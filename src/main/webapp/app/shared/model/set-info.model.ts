export interface ISetInfo {
  id?: number;
  geslacht?: string;
  naam?: string;
  achternaam?: string;
  postCode?: string;
  huisNummer?: string;
  straat?: string;
  plaats?: string;
  telefoon?: string;
  email?: string;
  contract?: string;
  telefonisch?: boolean;
  taal?: string;
  verzendMethode?: string;
  medewerkerId?: number;
}

export class SetInfo implements ISetInfo {
  constructor(
    public id?: number,
    public geslacht?: string,
    public naam?: string,
    public achternaam?: string,
    public postCode?: string,
    public huisNummer?: string,
    public straat?: string,
    public plaats?: string,
    public telefoon?: string,
    public email?: string,
    public contract?: string,
    public telefonisch?: boolean,
    public taal?: string,
    public verzendMethode?: string,
    public medewerkerId?: number
  ) {
    this.telefonisch = this.telefonisch || false;
  }
}
