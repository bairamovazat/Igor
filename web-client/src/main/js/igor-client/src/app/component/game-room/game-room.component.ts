import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {NewGame} from "../../model/new-game";
import {Person} from "../../model/person";
import {GameSocketAdapter} from "../../model/game-socket-adapter";
import {WebsocketService} from "../../service/websocket.service";
import {ChosePerson} from "../../model/game-action/chose-person";
import {ActivatedRoute, Router} from "@angular/router";
import {GameService} from "../../service/game.service";
import {Url} from "../../enum/url.enum";

@Component({
  selector: 'app-game-room',
  templateUrl: './game-room.component.html',
  styleUrls: ['./game-room.component.css']
})
export class GameRoomComponent implements OnInit {

  public currentGame: NewGame;

  public personIsChosen: boolean = false;

  public selectPersonCallBack;

  public gameSocketAdapter: GameSocketAdapter;

  constructor(
    public webSocketService: WebsocketService,
    private route: ActivatedRoute,
    private gameService:GameService,
    private router: Router
  ) {
    let that = this;
    this.selectPersonCallBack = (selectedList: Person[]) => {
      let chosePerson: ChosePerson = new ChosePerson(selectedList);
      that.gameSocketAdapter.chosePerson(chosePerson);
      that.personIsChosen = true;
    };
  }

  ngOnInit(): void {
    let that = this;
    this.route.paramMap.subscribe(params => {
      let gameId = params.get('id');
      console.log(gameId);
      that.currentGame = that.gameService.getGameById(gameId);
      console.log(that.currentGame);
      // if(this.currentGame == null) {
      //   this.router.navigate([Url.home])
      // }
      that.gameSocketAdapter = new GameSocketAdapter(that.currentGame, that.webSocketService);
      console.log(that.gameSocketAdapter);
    });
  }

}
