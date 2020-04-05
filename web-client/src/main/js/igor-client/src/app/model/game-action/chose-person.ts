import {Person} from "../person";

export class ChosePerson {
  public actionName:String = "ChosePerson";
  public personList:Person[];

  constructor(personList: Person[]) {
    this.personList = personList;
  }
}
