import {Component, OnInit} from '@angular/core';
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
  public name: string = "";
  public lastName: string = "";
  public login: string = "";
  public password: string = "";

  constructor(private _authenticationService: AuthenticationService,
              private _notificationsService: NotificationsService,
              private _restService: RestService,
              private _router: Router) {
  }

  ngOnInit(): void {
  }

  register(): void {
    let form: UserForm = new UserForm(this.name, this.lastName, this.login, this.password);

    var valid = true;

    if (!this.name.match("^[A-Za-zА-Яа-я]{2,}$")) {
      this._notificationsService.error("", "Имя должно содержать не менее 2 символов (латиница и кириллица).");
      valid = false;
    }

    if (!this.lastName.match("^[A-Za-zА-Яа-я]{2,}$")) {
      this._notificationsService.error("", "Фамилия должна содержать не менее 2 символов (латиница и кириллица).");
      valid = false;
    }

    if (!this.login.match("^[A-Za-z0-9]{2,15}$")) {
      this._notificationsService.error("", "Логин должен содержать 2-15 символов (латинские буквы и цифры).");
      valid = false;
    }

    if (!this.password.match("^[A-Za-z0-9]{6,}$")) {
      this._notificationsService.error("", "Пароль должен содержать не менее 6 символов (латинские буквы и цифры).");
      valid = false;
    }

    if (valid) {
      this._restService.registerUser(form)
        .subscribe(data => {
          this._notificationsService.success("", "Успешная регистрация");
          this._router.navigate([Url.home]);
        }, error => {
          console.error(error);
          this._notificationsService.error("", "Данный логин уже существует");
        })
    }
  }
}
