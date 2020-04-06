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

  private _userLogin: string;

  ngOnInit(): void {

  }

  public inviteUser() {
    if (!this._userLogin.match("^[A-Za-z0-9]{2,15}$")) {
      this._notificationsService.error("", "Логин должен содержать 2-15 символов (латинские буквы и цифры).");
    } else {
      // TODO: Добавить поиск id по нику в базе

      if (this._userLogin == null) {
        this._notificationsService.error("", "Пользователь не найден.");
      } else {
        this._websocketService.inviteUser(this._userLogin);
      }
    }
  }


  get userLogin(): string {
    return this._userLogin;
  }

  set userLogin(value: string) {
    this._userLogin = value;
  }
}
