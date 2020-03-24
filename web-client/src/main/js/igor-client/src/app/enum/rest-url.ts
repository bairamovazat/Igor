export class RestUrl {
  private static API_PATH: string = "/api/v1";

  private static USER_PATH = RestUrl.API_PATH + "/user";
  public static USER = {
    Test: RestUrl.USER_PATH + "/test",
    Create: RestUrl.USER_PATH,
  };

  private static TOKEN_PATH: string = RestUrl.API_PATH + "/token";
  public static TOKEN = {
    Create: RestUrl.TOKEN_PATH,
  }
}
