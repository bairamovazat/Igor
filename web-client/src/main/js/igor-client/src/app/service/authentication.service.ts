import {Injectable} from '@angular/core';
import {Token} from "../model/token";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private _token:Token = null;

  constructor() {
  }

  public authenticate(token:Token) {
    this._token = token;
  }
  public isAuthenticated(): boolean {
    return this._token != null;
  }

  public getToken(): string {
    return "";
  }
}
