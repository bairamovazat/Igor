import {environment} from "../../environments/environment";

export class WebsocketUrl {
  public static WEBSOCKET_PATH: string = environment.websocketEndpoint;

  public static appPrefix:string = "/app";
  public static subscribePrefix:string = "/subscribe";

  public static inviteUser:string = "/app/invite/create";
  public static acceptInvite:string = "/app/invite/accept";

  public static subscribeIncomingInvite:string = "/user/queue/invite/incoming";
  public static subscribeNewGame:string = "/user/queue/game/new-game";


}

