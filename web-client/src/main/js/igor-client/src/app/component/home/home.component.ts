import { Component, OnInit } from '@angular/core';
import {RestService} from "../../service/rest.service";
import {User} from "../../model/user";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private _user:User = null;

  constructor(private _restService:RestService) {
    this.updateUser();
  }

  ngOnInit(): void {
  }

  private updateUser() {
    return this._restService.getCurrentUser()
      .subscribe(data => {
        this._user = data;
      }, error => {
        console.error(error);
      });
  }

  get user(): User {
    return this._user;
  }

}
