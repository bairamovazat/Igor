import {NewGame} from "./new-game";
import {WebsocketService} from "../service/websocket.service";
import {Person} from "./person";
import * as Stomp from 'stompjs';
import {WebsocketUrl} from "../enum/websocket-url";
import {ChosePerson} from "./game-action/chose-person";
import {Attack} from "./game-action/attack";
import {EnemyPlayerChosePerson} from "./game-events/enemy-player-chose-person";
import {GameUrl} from "../enum/game-url";
import {EnemyPlayerStep} from "./game-events/enemy-player-step";
import {GameEnd} from "./game-events/game-end";
import {StartGame} from "./game-events/start-game";
import {YourStep} from "./game-events/your-step";
import {EnemyPlayerAttack} from "./game-events/enemy-player-attack";

export class GameSocketAdapter {

  public currentGame: NewGame;
  private _stompClient: Stomp;

  private _personIsSelected: boolean = false;
  private _enemyPlayerPersonIsSelected: boolean = false;

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
    this.stompClient.send(
      this.formatUrl(urs), {}, JSON.stringify(object)
    );
  }



  public subscribeToGame() {
    this.stompClient.subscribe(GameUrl.subscribeEnemyPlayerChosePerson, (message) => {
      let enemyPlayerChosePerson: EnemyPlayerChosePerson = new EnemyPlayerChosePerson(message.person);
      console.log(enemyPlayerChosePerson);

    });

    this.stompClient.subscribe(GameUrl.subscribeEnemyPlayerStep, (message) => {
      let enemyPlayerStep: EnemyPlayerStep = new EnemyPlayerStep();
      console.log(enemyPlayerStep);

    });

    this.stompClient.subscribe(GameUrl.subscribeGameEnd, (message) => {
      let gameEnd: GameEnd = new GameEnd(message.win, message.experience);
      console.log(gameEnd);

    });

    this.stompClient.subscribe(GameUrl.subscribeStartGame, (message) => {
      let startGame: StartGame = new StartGame();
      console.log(startGame);

    });

    this.stompClient.subscribe(GameUrl.subscribeYourStep, (message) => {
      let yourStep: YourStep = new YourStep();
      console.log(yourStep);

    });

    this.stompClient.subscribe(GameUrl.subscribeEnemyPlayerAttack, (message) => {
      let playerAttack: EnemyPlayerAttack = new EnemyPlayerAttack(message.xAbscissa, message.yOrdinate, message.Person);
      console.log(playerAttack);

    });
  }

  set stompClient(value: Stomp) {
    this._stompClient = value;
  }

  public sendAttack(attack: Attack) {
    this.send(GameUrl.sendAttack, JSON.stringify(attack));
  }

  public chosePerson(chosePerson: ChosePerson) {
    this.send(GameUrl.sendChosePerson, JSON.stringify(chosePerson));
  }

}
