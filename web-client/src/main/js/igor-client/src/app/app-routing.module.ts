import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {Url} from "./enum/url.enum";
import {LoginComponent} from "./component/login/login.component";
import {HomeComponent} from "./component/home/home.component";
import {AnonymousGuard} from "./guard/anonymous.guard";
import {AuthenticationGuard} from "./guard/authentication.guard";
import {RegistrationComponent} from "./component/registration/registration.component";

const routes: Routes = [
  {path: '', redirectTo: Url.home, pathMatch: "full"},
  {path: Url.login, component: LoginComponent, canActivate: [AnonymousGuard]},
  {path: Url.registration, component: RegistrationComponent, canActivate: [AnonymousGuard]},
  {path: Url.home, component: HomeComponent, canActivate: [AuthenticationGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AnonymousGuard, AuthenticationGuard]
})
export class AppRoutingModule {
}
