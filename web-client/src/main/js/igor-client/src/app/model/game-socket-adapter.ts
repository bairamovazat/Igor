import {NewGame} from "./new-game";
import {WebsocketService} from "../service/websocket.service";
import {Person} from "./person";
import * as Stomp from 'stompjs';
import {ChosePerson} from "./game-action/chose-person";
import {Attack} from "./game-action/attack";
import {EnemyPlayerChosePerson} from "./game-events/enemy-player-chose-person";
import {GameUrl} from "../enum/game-url";
import {GameEnd} from "./game-events/game-end";
import {EnemyPlayerAttack} from "./game-events/enemy-player-attack";
import {emitKeypressEvents} from "readline";

export class GameSocketAdapter {

  public currentGame: NewGame;
  private _stompClient: Stomp;

  private _personIsSelected: boolean = false;
  private _enemyPlayerPersonIsSelected: boolean = false;

  public gameStarted:boolean = false;
  public yourStep:boolean = false;
  public gameEnd:GameEnd = null;
  public currentPersonId:number = -1;

  constructor(currentGame: NewGame,
              websocketService: WebsocketService) {
    this.currentGame = currentGame;
    websocketService.fillStompClient(this);
    this.subscribeToGame();
  }

  private formatUrl(url: string) {
    return url + "/" + this.currentGame.id;
  }

  private send(urs: string, object: any) {
    console.log(object);
    console.log(JSON.stringify(object));
    this._stompClient.send(
      this.formatUrl(urs), {}, JSON.stringify(object)
    );
  }

  public subscribeToGame() {
    let that = this;
    this._stompClient.subscribe(this.formatUrl(GameUrl.subscribeEnemyPlayerChosePerson), (message) => {
      let body:JSON = JSON.parse(message.body);
      let enemyPlayerChosePerson: EnemyPlayerChosePerson = new EnemyPlayerChosePerson(Person.fromJsonArray(body["personList"]));
      that.currentGame.enemyPlayerPersons = enemyPlayerChosePerson.personList;
    });

    this._stompClient.subscribe(this.formatUrl(GameUrl.subscribeEnemyPlayerStep), (message) => {
      that.yourStep = false;
    });

    this._stompClient.subscribe(this.formatUrl(GameUrl.subscribeGameEnd), (message) => {
      let body:JSON = JSON.parse(message.body);
      that.gameEnd = new GameEnd(body["win"], body["experience"]);
    });

    this._stompClient.subscribe(this.formatUrl(GameUrl.subscribeStartGame), (message) => {
      that.gameStarted = true;
    });

    this._stompClient.subscribe(this.formatUrl(GameUrl.subscribeYourStep), (message) => {
      let body:JSON = JSON.parse(message.body);
      that.currentPersonId = body["personId"];
      that.yourStep = true;
    });

    this._stompClient.subscribe(this.formatUrl(GameUrl.subscribeEnemyPlayerAttack), (message) => {
      let body:JSON = JSON.parse(message.body);

      let playerAttack: EnemyPlayerAttack = new EnemyPlayerAttack(body["xAbscissa"], body["yOrdinate"], Person.fromJson(body["person"]));
      that.processEnemyPlayerAttack(playerAttack)
    });
  }

  public processEnemyPlayerAttack(enemyPlayerAttack: EnemyPlayerAttack) {
    let yourPersonIndex:number = this.currentGame.yourPersons.findIndex(e => e.id == enemyPlayerAttack.person.id);
    let yourPerson:Person = this.currentGame.yourPersons[yourPersonIndex];
    yourPerson.currentHealth = yourPerson.currentHealth - enemyPlayerAttack.person.attack;
  }

  set stompClient(value: Stomp) {
    this._stompClient = value;
  }

  public sendAttack(attack: Attack) {
    console.log(attack)
    this.send(GameUrl.sendAttack, attack);
  }

  public chosePerson(chosePerson: ChosePerson) {
    this.send(GameUrl.sendChosePerson, chosePerson);
    this.currentGame.yourPersons = chosePerson.personList;
  }

}
