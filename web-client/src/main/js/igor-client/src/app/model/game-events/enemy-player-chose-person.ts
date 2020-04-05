import {Person} from "../person";

export class EnemyPlayerChosePerson {
  public eventName:String = "EnemyPlayerChosePerson";
  public personList:Person[] = [];

  constructor(personList: Person[]) {
    this.personList = personList;
  }
}
