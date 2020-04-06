import { Component } from '@angular/core';
import {AuthenticationService} from "./service/authentication.service";
import {Url} from "./enum/url.enum";
import {WebsocketService} from "./service/websocket.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'igor-client';

  constructor(private _authenticationService:AuthenticationService,
              private _websocketService:WebsocketService) {
  }

  get isAuthenticated(): boolean {
    return this._authenticationService.isAuthenticated;
  }
}
