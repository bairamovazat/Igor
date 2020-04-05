import {Component, OnInit} from '@angular/core';
import {NotificationsService} from "angular2-notifications";
import {WebsocketService} from "../../service/websocket.service";

@Component({
  selector: 'app-game-search',
  templateUrl: './game-search.component.html',
  styleUrls: ['./game-search.component.css']
})
export class GameSearchComponent implements OnInit {

  constructor(
    private _notificationsService: NotificationsService,
    private _websocketService: WebsocketService
  ) {
  }

  private _nickName: String;
  private _userId: Number;

  ngOnInit(): void {

  }

  public inviteUser() {
    if (!this._nickName.match("^[A-Za-z0-9]{8,15}$")) {
      this._notificationsService.error("", "Логин должен содержать 8-15 символов (латинские буквы и цифры).");
    } else {
      // TODO: Добавить поиск id по нику в базе

      if (this._userId == null) {
        this._notificationsService.error("", "Пользователь не найден.");
      } else {
        this._websocketService.inviteUser(this._userId);
      }
    }
  }

  get userId(): Number {
    return this._userId;
  }

  set userId(value: Number) {
    this._userId = value;
  }
}
