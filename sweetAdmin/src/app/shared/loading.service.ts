/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';

@Injectable()
export class LoadingService {
  public loadingDiv: HTMLDivElement;
  public isShow: Boolean = false;
  constructor() {
      this.loadingDiv = document.createElement('div');
      this.loadingDiv.id = 'loadingComponent';
      this.loadingDiv.setAttribute('style', 'position: fixed;left:0;top:0;right: 0;bottom: 0;margin: auto;width:50px;height:50px;border-radius: 10px;background-color: rgba(0,0,0,0);z-index:9999999;');
      this.loadingDiv.innerHTML = '<div style="position: absolute;left:0;top:0;right: 0;bottom: 0;margin: auto;width: 50px;height: 50px"><div class="double-bounce1"></div><div class="double-bounce2"></div></div>';
  }
  show() {
    if (!this.isShow) {
      document.body.appendChild(this.loadingDiv);
      this.isShow = true;
    }
  }
  hide() {
    if (this.isShow) {
      document.body.removeChild(this.loadingDiv);
      this.isShow = false;
    }
  }
}
