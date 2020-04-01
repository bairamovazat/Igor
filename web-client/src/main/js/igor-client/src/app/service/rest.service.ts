import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {RestUrl} from "../enum/rest-url";
import {Token} from "../model/token";
import {LoginForm} from "../model/login-form";
import {Observable} from "rxjs";
import {UserForm} from "../model/user-form";
import {environment} from '../../environments/environment';
import {User} from "../model/user";
import {AuthenticationService} from "./authentication.service";

@Injectable({
  providedIn: 'root'
})
export class RestService {

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) {
  }

  public createToken(loginForm: LoginForm): Observable<Token> {
    return this.http.get<Token>(RestUrl.TOKEN.Create, this.optionsFromParams(loginForm));
  }


  public registerUser(userForm: UserForm): Observable<void> {
    return this.http.post<void>(RestUrl.USER.Create, userForm, this.optionsFromParams(null));
  }

  private optionsFromParams(params: any) {
    let headers:HttpHeaders = null;
    if(this.authenticationService.getToken() != null) {
      headers = this.headers.set("token", this.authenticationService.getToken());
    } else {
      headers = this.headers.delete("token");
    }
    return {
      headers: headers,
      params: params
    };
  }

  getCurrentUser() {
    return this.http.get<User>(RestUrl.USER.Current, this.optionsFromParams(null));
  }
}
