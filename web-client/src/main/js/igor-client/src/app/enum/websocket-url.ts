import {environment} from "../../environments/environment";

export class WebsocketUrl {
  public static WEBSOCKET_PATH: string = environment.websocketEndpoint;

  public static appPrefix:string = "/app";
  public static subscribePrefix:string = "/subscribe";

  public static inviteUser:string = "/app/invite/create";
  public static subscribeIncomingInvite:string = "/user/queue/invite/incoming";

}

