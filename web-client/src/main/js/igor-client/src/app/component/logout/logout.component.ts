import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from "../../service/authentication.service";
import {Router} from "@angular/router";
import {Url} from "../../enum/url.enum";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private _authenticationService:AuthenticationService,
              private _router: Router) {
    _authenticationService.logout();
  }

  ngOnInit(): void {
    this._router.navigate([Url.login]);
  }

}
