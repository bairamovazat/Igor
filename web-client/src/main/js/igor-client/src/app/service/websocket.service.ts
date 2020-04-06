import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client'
import {NotificationsService} from "angular2-notifications";
import {WebsocketUrl} from "../enum/websocket-url";
import {AuthenticationService} from "./authentication.service";
import {Token} from "../model/token";
import {GameInvite} from "../model/game-invite";
import {GameSocketAdapter} from "../model/game-socket-adapter";
import {NotifService} from "./notif.service.extence";
import {GameService} from "./game.service";
import {NewGame} from "../model/new-game";
import {User} from "../model/user";
import {Person} from "../model/person";
import {GameMap} from "../model/game-map";
import {Router} from "@angular/router";
import {Url} from "../enum/url.enum";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private serverUrl = WebsocketUrl.WEBSOCKET_PATH;
  private socket: SockJS;
  private stompClient;
  private _isConnected: boolean = false;

  constructor(private _notificationsService: NotificationsService,
              private notifServiceExt: NotifService,
              private _gameService: GameService,
              private _router: Router) {
    // this.initializeWebSocketConnection();
  }

  public initializeWebSocketConnection(token: Token) {
    if (this.socket != null) {
      this.socket.close();
      this.socket = null;
      this.stompClient = null
    }
    let headers = {
      "token": token.value
    };
    //TODO переделать на header
    this.socket = new SockJS(this.serverUrl + "?token=" + token.value);
    this.stompClient = Stomp.over(this.socket);

    let login = "login";
    let passcode = "passcode";
    this.stompClient.connect({
        login: login, passcode: passcode
      },
      frame => {
        this.subscribe();
        this._isConnected = true;
      }, error => {
        this._isConnected = false;
        console.log(error);
        // this._notificationsService.error("Соединение", "Ошибка создания подключения");
      }
    );
  }

  private subscribe() {
    this.stompClient.subscribe(WebsocketUrl.subscribeIncomingInvite, (message) => {
      let data = JSON.parse(message.body);

      this.notifServiceExt.action(
        'Битва c ' + data.initiatorLogin + '!',
        `Начать поединок: <button class="btn btn-primary">Вперёд!</button>`,
        'btn',
        () => this.acceptInvite(data)
      )
    });
    this.stompClient.subscribe(WebsocketUrl.subscribeNewGame, (message) => {
      let data = JSON.parse(message.body);
      const newGame: NewGame = NewGame.fromJson(data);
      console.log(newGame);
      this._gameService.includeGameToStorage(newGame);
      this._router.navigate([Url.game + "/" + newGame.id]);
    });
  }

  public acceptInvite(gameInvite: GameInvite) {
    this.stompClient.send(WebsocketUrl.acceptInvite, {}, JSON.stringify(gameInvite));
  }

  public inviteUser(userLogin: string) {
    let gameInvite: GameInvite = new GameInvite();
    gameInvite.invitedLogin = userLogin;
    this.stompClient.send(WebsocketUrl.inviteUser, {}, JSON.stringify(gameInvite));
  }

  get isConnected(): boolean {
    return this._isConnected;
  }

  set isConnected(value: boolean) {
    this._isConnected = value;
  }

  public fillStompClient(gameSocketAdapter: GameSocketAdapter) {
    gameSocketAdapter.stompClient = this.stompClient;
  }
}
