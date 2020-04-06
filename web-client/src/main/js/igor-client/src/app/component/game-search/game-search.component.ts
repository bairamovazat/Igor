import {Component, OnInit} from '@angular/core';
import {NotificationsService} from "angular2-notifications";
import {WebsocketService} from "../../service/websocket.service";
import {RestService} from "../../service/rest.service";
import {UserForm} from "../../model/user-form";
import {GameInvite} from "../../model/game-invite";

@Component({
  selector: 'app-game-search',
  templateUrl: './game-search.component.html',
  styleUrls: ['./game-search.component.css']
})
export class GameSearchComponent implements OnInit {
  public _incomingGameEvents:GameInvite[] = [];
  public _outgoingGameEvents:GameInvite[] = [];

  constructor(
    private _notificationsService: NotificationsService,
    private _websocketService: WebsocketService,
    private _restService: RestService,

) {}

  private _userId: Number;

  ngOnInit(): void {
    this._restService.incoming()
      .subscribe(data => {
        this._incomingGameEvents = data;
        console.log(data);
      }, error => {
        console.error(error);
        this._notificationsService.error("", "Некорректные данные")
      });

    this._restService.outgoing()
      .subscribe(data => {
        this._outgoingGameEvents = data;
        console.log(data);
      }, error => {
        console.error(error);
        this._notificationsService.error("", "Некорректные данные")
      })

  }

  public inviteUser() {
    if (this._userId == null || this._userId == 0) {
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
