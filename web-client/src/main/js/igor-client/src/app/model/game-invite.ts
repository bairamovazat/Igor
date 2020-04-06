export class GameInvite {
  public id: string;

  //Кто отпраивл приглашение
  public initiator: Number;

  //Кто получит
  public invited: Number;

  public invitedLogin:string;

  public invitedUserAccept: Number;

  public closed: Number;
}
