import {Person} from "../person";

export class EnemyPlayerAttack {
  public actionName:String = "EnemyPlayerAttack";
  public xAbscissa:number = null;
  public yOrdinate:number = null;
  public person:Person = null;

  constructor(xAbscissa: number, yOrdinate: number, person: Person) {
    this.xAbscissa = xAbscissa;
    this.yOrdinate = yOrdinate;
    this.person = person;
  }
}
