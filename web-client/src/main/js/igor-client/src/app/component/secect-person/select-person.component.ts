import {Component,  Input, OnInit} from '@angular/core';
import {Person} from "../../model/person";
import {NotificationsService} from "angular2-notifications";
import {PersonAssetsSrc} from "../../enum/assets-reference.enum";

@Component({
  selector: 'app-select-person',
  templateUrl: './select-person.component.html',
  styleUrls: ['./select-person.component.css']
})
export class SelectPersonComponent implements OnInit {

  @Input() personList:Person[];
  @Input() returnValues: (selectedList:Person[]) => void;

  public selectedPersonList:Person[] = [];

  constructor(private _notificationsService:NotificationsService) {
  }

  ngOnInit(): void {
  }

  selectPersons() {
    if(this.selectedPersonList.length != 3) {
      this._notificationsService.error("Внимание!","Выберите 3 персонажей, а не " + this.selectedPersonList.length);
    } else {
      this.returnValues(this.selectedPersonList);
    }
  }

  selectPerson(person: Person) {
    let index = this.selectedPersonList.findIndex(e => e.id === person.id);
    if(index != -1) {
      this.selectedPersonList.splice(index, 1);
    } else {
      this.selectedPersonList.push(person);
    }
  }

  getPersonStyle(person: Person) {
    if(this.selectedPersonList.find(e => e.id === person.id) != undefined) {
      return "person-selected"
    } else {
      return "person-none-selected"
    }
  }
}
