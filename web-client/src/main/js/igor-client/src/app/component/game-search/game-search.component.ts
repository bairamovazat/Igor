import { Component, OnInit } from '@angular/core';
import {NotificationsService} from "angular2-notifications";
import {WebsocketService} from "../../service/websocket.service";

@Component({
  selector: 'app-game-search',
  templateUrl: './game-search.component.html',
  styleUrls: ['./game-search.component.css']
})
export class GameSearchComponent implements OnInit {

  constructor(
    private _notificationsService:NotificationsService,
    private _websocketService:WebsocketService
  ) { }

  private _userId:Number;

  ngOnInit(): void {

  }

  public inviteUser() {
    if(this._userId == null || this._userId == 0) {
      this._notificationsService.error("", "Введите Id пользователя");
    }
    this._websocketService.inviteUser(this._userId);
  }

  get userId(): Number {
    return this._userId;
  }

  set userId(value: Number) {
    this._userId = value;
  }
}
