export class GameMap {
  public width:number;
  public height:number;

  constructor(width?: number, height?: number) {
    this.width = width;
    this.height = height;
  }

  public static fromJson(object:JSON):GameMap{
    return Object.assign(new GameMap(), object);
  }
}
