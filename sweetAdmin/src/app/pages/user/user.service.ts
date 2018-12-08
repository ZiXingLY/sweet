/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class UserService {
    constructor(private http: Http) {
    }

    seePolicy(params): Observable<any> {
        return this.http.get(ROOT_URL + 'sys/admin/getUserAllScore', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 用户列表
    userList(params): Observable<any> {
        return this.http.get(ROOT_URL + 'sys/admin/userlist', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 禁用解除禁用
    userDisable(params): Observable<any> {
        return this.http.get(ROOT_URL + 'sys/admin/userForbid', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    listByUid(params): Observable<any> {
        return this.http.get(ROOT_URL + '/credits/listByUid', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
    getint(params): Observable<any> {
        return this.http.post(ROOT_URL + 'sys/admin/getUserAllScore', params)
            .map(this.extractData)
            .catch(this.handleError);
    }
    private extractData(res: Response) {
        const body = res.json();
        // if (body['code'] === 5003) {
        //   alert('用户信息失效');
        //   localStorage.token = '';
        //   localStorage.tokenTime = '';
        //   window.location.reload();
        // }
        return body;
    }

    private handleError(error: Response | any) {
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
