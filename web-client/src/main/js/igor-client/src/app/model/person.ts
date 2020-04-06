//Персонаж
import {PersonAssetsSrc} from "../enum/assets-reference.enum";

export class Person {
  public id: number;
  public type: string;
  public attack: number;
  public health: number;
  public name: string;

  public currentHealth: number;

  constructor(id?: number, type?: string, attack?: number, health?: number, name?: string) {
    this.id = id;
    this.type = type;
    this.attack = attack;
    this.health = health;
    this.name = name;
    this.currentHealth = health;
  }

  getPersonImageSrc() {
    return "/assets/" + PersonAssetsSrc[this.type] + ".png";
  }

  public static fromJson(object: JSON): Person {
    let result: Person = Object.assign(new Person(), object);
    result.currentHealth = result.health;
    return result;
  }

  public static fromJsonArray(objectArray: JSON): Person[] {
    let result: Person[] = [];
    for (let key in objectArray) {
      console.log(key);
      console.log(objectArray[key])
      result.push(Person.fromJson(objectArray[key]));
    }
    return result;
  }
}
