import {Person} from "../person";

export class Attack {
  public actionName: String = "Attack";
  public x: number = null;
  public y: number = null;
  public person:Person = null;

  constructor(x: number, y: number, person: Person) {
    this.x = x;
    this.y = y;
    this.person = person;
  }
}
