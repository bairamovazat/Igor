import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {NotificationsService} from "angular2-notifications";
import {RestService} from "../../service/rest.service";
import {LoginForm} from "../../model/login-form";
import {Token} from "../../model/token";
import {Router} from "@angular/router";
import {Url} from "../../enum/url.enum";

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
              private _router: Router) {

  }

  ngOnInit(): void {

  }

  login():void {
    let form:LoginForm = new LoginForm(this.loginValue, this.passwordValue);
    this._restService.createToken(form)
      .subscribe(data => {
        this._authenticationService.authenticate(data);
        this._notificationsService.success("","Успешная авторизация");
        this._router.navigate([Url.home]);
      }, error => {
        console.error(error);
        this._notificationsService.error("", "Неверный логин или пароль");
      })
  }

}
