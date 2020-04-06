import {User} from "./user";
import {Person} from "./person";
import {GameMap} from "./game-map";

export class NewGame {

  public id: string;
  public createDate: Date;
  public enemyPlayer: User;
  public personList: Person[];
  public gameMap: GameMap;
  public yourPersons: Person[];
  public enemyPlayerPersons: Person[];

  constructor(id: string, createDate: Date, enemyPlayer: User, personList: Person[], gameMap: GameMap, yourPersons: Person[], enemyPlayerPersons: Person[]) {
    this.id = id;
    this.createDate = createDate;
    this.enemyPlayer = enemyPlayer;
    this.personList = personList;
    this.gameMap = gameMap;
    this.yourPersons = yourPersons;
    this.enemyPlayerPersons = enemyPlayerPersons;
  }
}
