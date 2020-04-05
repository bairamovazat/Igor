export class GameEnd {
  public eventName:String = "GameEnd";

  public win:boolean = false;
  public experience:number = 0;

  constructor(win: boolean, experience: number) {
    this.win = win;
    this.experience = experience;
  }
}
