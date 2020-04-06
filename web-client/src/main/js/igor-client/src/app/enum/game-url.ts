export class GameUrl {
  private static base = "/app/game";
  private static subscribe = "/user/queue/game";

  public static sendChosePerson: string = GameUrl.base + "/ChosePerson";
  public static sendAttack: string = GameUrl.base + "/Attack";

  public static subscribeEnemyPlayerChosePerson: string = GameUrl.subscribe + "/EnemyPlayerChosePerson";
  public static subscribeEnemyPlayerStep: string = GameUrl.subscribe + "/EnemyPlayerStep";
  public static subscribeGameEnd: string = GameUrl.subscribe + "/GameEnd";
  public static subscribeStartGame: string = GameUrl.subscribe + "/StartGame";
  public static subscribeYourStep: string = GameUrl.subscribe + "/YourStep";
  public static subscribeEnemyPlayerAttack: string = GameUrl.subscribe + "/EnemyPlayerAttack";
}
