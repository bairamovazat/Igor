package com.apeiron.igor.enums;

public class GameUrl {

    public final static String sendChosePerson = "/game/ChosePerson";
    public final static String sendAttack = "/game/Attack";

    public final static String subscribeEnemyPlayerChosePerson = "/queue/game/EnemyPlayerChosePerson";
    public final static String subscribeEnemyPlayerStep = "/queue/game/EnemyPlayerStep";
    public final static String subscribeGameEnd = "/queue/game/GameEnd";
    public final static String subscribeStartGame =  "/queue/game/StartGame";
    public final static String subscribeYourStep = "/queue/game/YourStep";
    public final static String subscribeEnemyPlayerAttack =  "/queue/game/EnemyPlayerAttack";
}
