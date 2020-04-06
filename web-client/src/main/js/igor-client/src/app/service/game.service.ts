import { Injectable } from '@angular/core';
import {NewGame} from "../model/new-game";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private _newGames:NewGame[] = [];

  constructor() {

  }

  public includeGameToStorage(newGame:NewGame) {
    this._newGames.push(newGame);
  }

  public getGameById(id:string) {
    return this._newGames[this._newGames.findIndex(e => e.id == id)];
  }

  public deleteGameById(id:string) {
    return this._newGames.slice(this._newGames.findIndex(e => e.id == id), 1);
  }
}
