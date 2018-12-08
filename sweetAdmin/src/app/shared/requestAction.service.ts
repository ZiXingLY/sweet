/**
 * Created by zaq on 2017/5/12.
 */
import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import {ProcessService} from './process.service';

@Injectable()
export class RequestService {
  public observable: any;
  constructor(private http: Http, public processService: ProcessService) {}
  get(url: string, params: any): Observable<any[]> {
    return this.http.get(url, params)
        .map(this.extractData)
        .catch(this.handleError);
  }
  post(url: string, data: any, params?: any): Observable<any[]> {
    return this.http.post(url, data, params)
        .map(this.extractData)
        .catch(this.handleError);
  }
  http_process(url: string, data?: any, params?: any): Observable<any[]> {
    return Observable.create((observable) => {
      this.processService.show();
      this.processService.drawFrame(0);
      const formData = new FormData();
      for (const k of Object.keys(data)) {
        formData.append(k, data[k]);
      }
      const xhr = new XMLHttpRequest();
      xhr.upload.addEventListener('progress', () => {
        this.uploadProgress(event);
      }, false);
      xhr.addEventListener('load', () => (this.uploadComplete(event)) , false);
      xhr.addEventListener('error', this.uploadFailed, false);
      xhr.addEventListener('abort', this.uploadCanceled, false);
      xhr.open('POST', ROOT_URL + url);
      xhr.send(formData);
      this.observable = observable;
    });
  }
  // 上传中 上传进度控制
  uploadProgress(evt) {
    this.processService.drawFrame(evt.loaded / evt.total * 100);
  }
  // 上传完成
  uploadComplete(evt) {
    this.processService.drawFrame(100);
    setTimeout(() => {
      this.processService.hide();
      this.observable.next(evt.currentTarget.responseText);
    }, 200);
  }
  // 中止图片上传后
  uploadCanceled(evt) {
    console.log(evt);
  }
  // 上传失败
  uploadFailed(evt) {
    console.log(evt);
  }
  private extractData(res: Response) {
    const body = res.json();
    return body;
  }
  private handleError (error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
