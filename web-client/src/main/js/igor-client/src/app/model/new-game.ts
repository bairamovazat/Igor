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

  constructor(id?: string, createDate?: Date, enemyPlayer?: User, personList?: Person[], gameMap?: GameMap, yourPersons?: Person[], enemyPlayerPersons?: Person[]) {
    this.id = id;
    this.createDate = createDate;
    this.enemyPlayer = enemyPlayer;
    this.personList = personList;
    this.gameMap = gameMap;
    this.yourPersons = yourPersons;
    this.enemyPlayerPersons = enemyPlayerPersons;
  }

  public static fromJson(object:JSON):NewGame{
    let result:NewGame = new NewGame();
    result.id = object["id"];
    result.createDate = object["createDate"];
    result.enemyPlayer = User.fromJson(object["user"]);
    result.personList = Person.fromJsonArray(object["personList"]);
    result.gameMap = GameMap.fromJson(object["gameMap"]);
    result.yourPersons = Person.fromJsonArray(object["yourPersons"]);
    result.enemyPlayerPersons = Person.fromJsonArray(object["enemyPlayerPersons"]);
    return result;
  }
}
