import {Person} from "../person";

export class Attack {
  public actionName: String = "Attack";
  public xAbscissa: number = null;
  public yOrdinate: number = null;
  public person:Person = null;

  constructor(xAbscissa: number, yOrdinate: number, person: Person) {
    this.xAbscissa = xAbscissa;
    this.yOrdinate = yOrdinate;
    this.person = person;
  }
}
