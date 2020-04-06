export class User {
  private _id:number;
  private _login:string;
  private _name:string;
  private _password:string;
  private _email:string;

  constructor(id?: number, login?: string, name?: string, password?: string, email?: string) {
    this._id = id;
    this._login = login;
    this._name = name;
    this._password = password;
    this._email = email;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get login(): string {
    return this._login;
  }

  set login(value: string) {
    this._login = value;
  }

  get name(): string {
    return this._name;
  }

  set name(value: string) {
    this._name = value;
  }

  get password(): string {
    return this._password;
  }

  set password(value: string) {
    this._password = value;
  }

  get email(): string {
    return this._email;
  }

  set email(value: string) {
    this._email = value;
  }

  public static fromJson(object:JSON):User{
    return Object.assign(new User(), object);
  }
}
