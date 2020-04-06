import {Injectable} from '@angular/core';
import {Token} from "../model/token";
import {User} from "../model/user";
import {WebsocketService} from "./websocket.service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private _token: Token = null;
  private _user: User = null;
  private _connectedSuccess: boolean = null;

  constructor(private _websocketService:WebsocketService) {
    this.loadFromLocalStorage();
  }

  public authenticate(token: Token) {
    this._token = token;
    try {
      localStorage.setItem("token", token.value)
    } catch (ignore) {
    }
    this._websocketService.initializeWebSocketConnection(token);
  }

  public setCurrentUser(user: User) {
    this._user = user;
  }

  public logout() {
    this._token = null;
    try {
      localStorage.removeItem("token")
    } catch (ignore) {
    }
  }

  private loadFromLocalStorage() {
    let token = localStorage.getItem("token");
    if (token) {
      this._token = new Token(token);
      this._websocketService.initializeWebSocketConnection(this._token);
    }
  }

  get isAuthenticated(): boolean {
    return this._token != null;
  }

  public getToken(): string {
    return this._token == null ? null : this._token.value;
  }

  public getCurrentUser(): User {
    return null;
  }

  get connectedSuccess(): boolean {
    return this._connectedSuccess;
  }

  set connectedSuccess(value: boolean) {
    this._connectedSuccess = value;
  }
}
