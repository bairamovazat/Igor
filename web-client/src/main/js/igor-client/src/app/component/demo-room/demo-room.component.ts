import {Component, OnInit} from '@angular/core';
import {NewGame} from "../../model/new-game";
import {User} from "../../model/user";
import {Person} from "../../model/person";
import {GameMap} from "../../model/game-map";

@Component({
  selector: 'app-demo-room',
  templateUrl: './demo-room.component.html',
  styleUrls: ['./demo-room.component.css']
})
export class DemoRoomComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

  getCurrentGame(): NewGame {
    let personList: Person[] = [];
    personList.push(new Person(0, "0", 1, 2, "name0"));
    personList.push(new Person(1, "1", 1, 2, "name1"));
    personList.push(new Person(2, "2", 1, 2, "name2"));
    personList.push(new Person(3, "3", 1, 2, "name3"));
    personList.push(new Person(4, "4", 1, 2, "name4"));
    personList.push(new Person(5, "5", 1, 2, "name5"));

    return new NewGame("1",
      new Date(),
      new User(null, null, null, null, null),
      personList,
      new GameMap(5, 5),
      [],
      []
    );
  }
}
