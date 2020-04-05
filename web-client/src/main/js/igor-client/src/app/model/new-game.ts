import {User} from "./user";
import {Person} from "./person";
import {GameMap} from "./game-map";

export class NewGame {
  public id: string;
  public createDate: Date;
  public enemyPlayer: User;
  public personList: Person[];
  public gameMap: GameMap;

  constructor(id: string, createDate: Date, enemyPlayer: User, personList: Person[], gameMap: GameMap) {
    this.id = id;
    this.createDate = createDate;
    this.enemyPlayer = enemyPlayer;
    this.personList = personList;
    this.gameMap = gameMap;
  }
}
