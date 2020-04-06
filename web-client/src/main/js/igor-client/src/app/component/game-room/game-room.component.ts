import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {NewGame} from "../../model/new-game";
import {Person} from "../../model/person";
import {GameSocketAdapter} from "../../model/game-socket-adapter";
import {WebsocketService} from "../../service/websocket.service";
import {ChosePerson} from "../../model/game-action/chose-person";

@Component({
  selector: 'app-game-room',
  templateUrl: './game-room.component.html',
  styleUrls: ['./game-room.component.css']
})
export class GameRoomComponent implements OnInit {

  @Input() currentGame: NewGame;

  public personIsChosen: boolean = false;

  public selectPersonCallBack;

  public gameSocketAdapter: GameSocketAdapter;

  constructor(
    public _webSocketService: WebsocketService
  ) {
    let that = this;
    this.selectPersonCallBack = (selectedList: Person[]) => {
      let chosePerson: ChosePerson = new ChosePerson(selectedList);
      that.gameSocketAdapter.chosePerson(chosePerson);
      that.personIsChosen = true;
    };
  }

  ngOnInit(): void {
    this.gameSocketAdapter = new GameSocketAdapter(this.currentGame, this._webSocketService);
  }

}
