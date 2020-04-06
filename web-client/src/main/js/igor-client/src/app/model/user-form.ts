export class UserForm {
  public name:string;
  public lastName:string;
  public login:string;
  public password:string;

  constructor(name: string, lastName: string, login: string, password: string) {
    this.name = name;
    this.lastName = lastName;
    this.login = login;
    this.password = password;
  }

}
