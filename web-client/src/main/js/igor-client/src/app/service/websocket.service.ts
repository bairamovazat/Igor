import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client'
import {NotificationsService} from "angular2-notifications";
import {WebsocketUrl} from "../enum/websocket-url";
import {AuthenticationService} from "./authentication.service";
import {Token} from "../model/token";
import {GameInvite} from "../model/game-invite";
import {NotifService} from "./notif.service.extence";

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {

  private serverUrl = WebsocketUrl.WEBSOCKET_PATH;
  private socket: SockJS;
  private stompClient;
  private _isConnected: boolean = false;

  constructor(private _notificationsService: NotificationsService, private notifServiceExt: NotifService) {
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
        this._notificationsService.error("Соединение", "Ошибка создания подключения");
      }
    );
  }

  private subscribe() {
    this.stompClient.subscribe(WebsocketUrl.subscribeIncomingInvite, (message) => {
      this.notifServiceExt.action(
        'Битва!',
        `Начать поединок: <button class="btn btn-primary">Do it!</button>`,
        'btn',
        () => this.actionplaceholder()
      )
      //this._notificationsService.success("success", message);
      //this.actionplaceholder();
    });
  }

  private actionplaceholder(){
    this._notificationsService.success("success", "message")
  }

  public inviteUser(userId: Number) {
    let gameInvite: GameInvite = new GameInvite();
    gameInvite.invited = userId;
    this.stompClient.send(WebsocketUrl.inviteUser, {}, JSON.stringify(gameInvite));
  }


  get isConnected(): boolean {
    return this._isConnected;
  }

  set isConnected(value: boolean) {
    this._isConnected = value;
  }
}
