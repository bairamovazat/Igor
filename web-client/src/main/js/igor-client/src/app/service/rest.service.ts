import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RestUrl} from "../enum/rest-url";
import {Token} from "../model/token";
import {LoginForm} from "../model/login-form";
import {Observable} from "rxjs";
import {UserForm} from "../model/user-form";

@Injectable({
  providedIn: 'root'
})
export class RestService {

  private _baseUrl = "http://localhost:8000";

  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {
  }

  public createToken(loginForm: LoginForm): Observable<Token> {
    return this.http.get<Token>(this._baseUrl + RestUrl.TOKEN.Create, this.optionsFromParams(loginForm));
  }


  public registerUser(userForm: UserForm): Observable<void> {
    return this.http.post<void>(this._baseUrl + RestUrl.USER.Create, userForm, this.optionsFromParams(null));
  }

  private optionsFromParams(params:any) {
    return {
      headers: this.headers,
      params: params
    };
  }
}
