import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {NotificationsService} from "angular2-notifications";
import {LoginForm} from "../../model/login-form";
import {Token} from "../../model/token";
import {Router} from "@angular/router";
import {Url} from "../../enum/url.enum";
import {WebsocketService} from "../../service/websocket.service";
import {RestService} from "../../service/rest.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginValue: string = "";
  public passwordValue: string = "";

  constructor(private _authenticationService:AuthenticationService,
              private _notificationsService:NotificationsService,
              private _restService:RestService,
              private _websocketService:WebsocketService,
              private _router: Router) {
  }

  ngOnInit(): void {

  }

  login():void {
    let form:LoginForm = new LoginForm(this.loginValue, this.passwordValue);
    this._restService.createToken(form)
      .subscribe(data => {
        this._authenticationService.authenticate(data);
        this._restService.getCurrentUser()
          .subscribe(user => {
            this._authenticationService.setCurrentUser(user);
          }, error => {
            console.log(error)
          });
        this._notificationsService.success("","Успешная авторизация");
        this._router.navigate([Url.home]);
      }, error => {
        console.error(error);
        this._notificationsService.error("", "Неверный логин или пароль");
      })
  }

}
