/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';

@Injectable()
export class AlertService {
  public alertDiv: any;
  public isShow: Boolean = false;
  constructor() {
  }
  show(str) {
    if (!this.isShow) {
      this.alertDiv = document.createElement('div');
      this.alertDiv.id = 'loadingComponent';
      this.alertDiv.style = 'position: fixed;left:0;top:0;right: 0;bottom: 0;margin: auto;padding: 0 12px 0 12px;height: 50px;color: white;text-align:center;line-height: 50px;background-color: rgba(0,0,0,.5);border-radius: 3px;z-index: 9999';
      this.alertDiv.innerHTML = str;
      this.alertDiv.style.width = str.length * 15 + 30 + 'px';
      document.body.appendChild(this.alertDiv);
      this.isShow = true;
      setTimeout(() => {
        this.hide();
      }, 2000);
    }
  }
  hide() {
    if (this.isShow) {
      document.body.removeChild(this.alertDiv);
      this.isShow = false;
    }
  }
}
