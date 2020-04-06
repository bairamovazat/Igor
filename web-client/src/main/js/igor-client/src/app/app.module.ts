import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './component/login/login.component';
import {HomeComponent} from './component/home/home.component';
import {RegistrationComponent} from './component/registration/registration.component';
import {SimpleNotificationsModule} from 'angular2-notifications';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule} from "@angular/forms";
import { LogoutComponent } from './component/logout/logout.component';
import { GameSearchComponent } from './component/game-search/game-search.component';
import { GameRoomComponent } from './component/game-room/game-room.component';
import { SelectPersonComponent } from './component/secect-person/select-person.component';
import { DemoRoomComponent } from './component/demo-room/demo-room.component';
import { GamePlaygroundComponent } from './component/game-playground/game-playground.component';
import {NotifService} from "./service/notif.service.extence";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegistrationComponent,
    LogoutComponent,
    GameSearchComponent,
    GameRoomComponent,
    SelectPersonComponent,
    DemoRoomComponent,
    GamePlaygroundComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    BrowserAnimationsModule,
    SimpleNotificationsModule.forRoot()
  ],
  providers: [NotifService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
