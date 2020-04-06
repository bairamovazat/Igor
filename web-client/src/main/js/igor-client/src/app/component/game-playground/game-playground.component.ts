import {Component, Input, OnInit} from '@angular/core';
import {NewGame} from "../../model/new-game";
import {GameSocketAdapter} from "../../model/game-socket-adapter";
import {Person} from "../../model/person";
import {Attack} from "../../model/game-action/attack";

@Component({
  selector: 'app-game-playground',
  templateUrl: './game-playground.component.html',
  styleUrls: ['./game-playground.component.css']
})
export class GamePlaygroundComponent implements OnInit {

  @Input()
  public gameSocketAdapter: GameSocketAdapter;

  constructor() {
  }

  ngOnInit(): void {
  }

  getEnemyPlayerPersonClass() {
    if (this.gameSocketAdapter.yourStep) {
      return "selected-person";
    } else {
      return "not-selected-person";
    }
  }

  attackEnemyPerson(enemyPlayerPersons: Person) {
    if (this.gameSocketAdapter.yourStep) {

      let enemyPlayerPersonsIndex = this.gameSocketAdapter.currentGame.enemyPlayerPersons.findIndex(e => e.id == enemyPlayerPersons.id);
      let yourPersonIndex = this.gameSocketAdapter.currentGame.yourPersons.findIndex(e => e.id == this.gameSocketAdapter.currentPersonId);

      let enemyPerson: Person = this.gameSocketAdapter.currentGame.enemyPlayerPersons[enemyPlayerPersonsIndex];
      let yourPerson: Person = this.gameSocketAdapter.currentGame.yourPersons[yourPersonIndex];

      enemyPerson.currentHealth = enemyPerson.currentHealth - yourPerson.attack;

      let attack: Attack = new Attack(enemyPerson.id, enemyPerson.id, yourPerson);
      this.gameSocketAdapter.sendAttack(attack);
    }
  }

  getPersonClass(person: Person) {
    if (this.gameSocketAdapter.yourStep && this.gameSocketAdapter.currentPersonId == person.id) {
      return "chosen-person"
    } else {
      return "";
    }
  }
}
