import {Injectable} from '@angular/core'
import {DomSanitizer} from '@angular/platform-browser'
import {NotificationsService, NotificationType} from 'angular2-notifications'
import {Subscription} from "rxjs";


@Injectable()
export class NotifService {

  private idsToSubscription: Array<{id: string, sub: Subscription}> = []
  private defaultOptions = {
    clickToClose: true,
    showProgressBar: true,
    theClass: 'er-notification',
    timeOut: 3000
  }

  constructor(private sanitizer: DomSanitizer,
              private notificationsService: NotificationsService) {}

  getOptions() {
    return this.defaultOptions;
  }

  success(title: string, content: string, options?: any) {
    this.notificationsService.success(title, content, options);
  }

  error(title: string, content: string, options?: any) {
    this.notificationsService.error(title, content, options);
  }

  info(title: string, content: string, options?: any) {
    this.notificationsService.info(title, content, options);
  }

  //Beautiful popmessage
  action(title: string, content: string, actionClass: string, actionFct: Function) {
    const html = (`
            <div class="sn-title">${ title }<div>
            <div class="sn-content">${ content }<div>`);

    const options = {
      timeOut: 15000,
      clickToClose: false,
      id: `custom${ new Date().getTime() }`
    }

    const notification = this.notificationsService.html(html, NotificationType.Info, options);

    const sub = notification.click.subscribe((e: MouseEvent) => {
      if((<HTMLElement> e.target).className.indexOf(actionClass) > -1) {
        this.notificationsService.remove(notification.id);
        this.notifDestroyed(notification.id);
        actionFct();
      }
    })

    this.idsToSubscription.push({id: notification.id, sub});
    console.log("ADDED this.idsToSubscription ", this.idsToSubscription);
  }

  notifDestroyed(id: string) {
    const subItem = this.idsToSubscription.find(i => i.id === id);

    if(subItem && subItem.sub) {
      subItem.sub.unsubscribe();
      this.idsToSubscription = this.idsToSubscription.filter(i => i.id !== id);
    }
    console.log("DESTROYED this.idsToSubscription ", this.idsToSubscription);
  }
}
