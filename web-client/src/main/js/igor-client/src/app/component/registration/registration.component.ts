import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {NotificationsService} from "angular2-notifications";
import {RestService} from "../../service/rest.service";
import {LoginForm} from "../../model/login-form";
import {UserForm} from "../../model/user-form";
import {Url} from "../../enum/url.enum";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  public name:string = "";
  public lastName:string = "";
  public login: string = "";
  public password: string = "";

  constructor(private _authenticationService:AuthenticationService,
              private _notificationsService:NotificationsService,
              private _restService:RestService,
              private _router: Router) {
  }

  ngOnInit(): void {
  }

  register():void {
    let form:UserForm = new UserForm(this.name, this.lastName, this.login, this.password);
    this._restService.registerUser(form)
      .subscribe(data => {
        this._notificationsService.success("", "Успешная регистрация");
        this._router.navigate([Url.home]);
      }, error => {
        console.error(error);
        this._notificationsService.error("", "Неверный логин или пароль")
      })
  }
}
