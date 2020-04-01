import {Injectable} from '@angular/core';
import {Token} from "../model/token";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private _token: Token = null;

  constructor() {
    this.loadFromLocalStorage();
  }

  public authenticate(token: Token) {
    this._token = token;
    try {
      localStorage.setItem("token", token.value)
    } catch (ignore) {

    }
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
    if(token) {
      this._token = new Token(token);
    }
  }

  public isAuthenticated(): boolean {
    return this._token != null;
  }

  public getToken(): string {
    return this._token == null ? null : this._token.value;
  }
}
