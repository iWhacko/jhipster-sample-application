export interface IMedewerker {
  id?: number;
  naam?: string;
  achterNaam?: string;
}

export class Medewerker implements IMedewerker {
  constructor(public id?: number, public naam?: string, public achterNaam?: string) {}
}
