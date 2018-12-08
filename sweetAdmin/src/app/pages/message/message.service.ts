/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class MessageService {
    constructor(private http: Http) {
    }

    // 获取列表
    getList(params): Observable<any[]> {
        return this.http.get(ROOT_URL + 'manage/message/messageManage', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }


    // 删除
    deleteMessage(params): Observable<any[]> {
        return this.http.get(ROOT_URL + 'manage/message/deletes', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 删除
    getMessage(params): Observable<any[]> {
        return this.http.get(ROOT_URL + 'manage/message/messageManage', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 处理反馈
    readMessage(params): Observable<any[]> {
        return this.http.post(ROOT_URL + 'manage/message/read', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    private extractData(res: Response) {
        const body = res.json();
        if (body['code'] === 5003) {
            alert('用户信息失效');
            localStorage.token = '';
            localStorage.tokenTime = '';
            window.location.reload();
        }
        return body;
    }

    private handleError(error: Response | any) {
        // In a real world app, you might use a remote logging infrastructure
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        // console.error(errMsg);
        return Observable.throw(errMsg);
    }
}
