import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";

@Component({
  selector: 'app-game-room',
  templateUrl: './game-room.component.html',
  styleUrls: ['./game-room.component.css']
})
export class GameRoomComponent implements OnInit {


  constructor(private _enemyPlayer:User) {}

  ngOnInit(): void {

  }

}
