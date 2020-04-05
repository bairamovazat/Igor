//Персонаж
export class Person {
  public id:number;
  public type:string;
  public attack:number;
  public health:number;
  public name:string;

  constructor(id: number, type: string, attack: number, health: number, name: string) {
    this.id = id;
    this.type = type;
    this.attack = attack;
    this.health = health;
    this.name = name;
  }
}
