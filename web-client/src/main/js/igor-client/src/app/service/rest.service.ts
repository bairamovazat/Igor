import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RestUrl} from "../enum/rest-url";
import {Token} from "../model/token";
import {LoginForm} from "../model/login-form";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RestService {

  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {
  }

  public createToken(loginForm: LoginForm): Observable<Token> {
    return this.http.get<Token>(RestUrl.TOKEN.Create, this.optionsFromParams(loginForm));
  }

  private optionsFromParams(params:any) {
    return {
      headers: this.headers,
      params: params
    };
  }
}
