import {environment} from "../../environments/environment";
import {GameSearchComponent} from "../component/game-search/game-search.component";

export class RestUrl {

  private static API_PATH: string = environment.apiEndpoint;

  private static USER_PATH = RestUrl.API_PATH + "/user";
  public static USER = {
    Test: RestUrl.USER_PATH + "/test",
    Create: RestUrl.USER_PATH,
    Current: RestUrl.USER_PATH + "/current"
  };

  private static TOKEN_PATH: string = RestUrl.API_PATH + "/token";
  public static TOKEN = {
    Create: RestUrl.TOKEN_PATH,
  };

  private static GAME_SEARCH_PATH: string = RestUrl.API_PATH + "/game-search";
  public static GAME_SEARCH = {
    Incoming: RestUrl.GAME_SEARCH_PATH + "/incoming",
    Outgoing: RestUrl.GAME_SEARCH_PATH + "/outgoing"
  }

}
