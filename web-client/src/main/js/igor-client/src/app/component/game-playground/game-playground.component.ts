import {Component, Input, OnInit} from '@angular/core';
import {NewGame} from "../../model/new-game";
import {GameSocketAdapter} from "../../model/game-socket-adapter";

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

}
