/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class OperateSericve {
    constructor(private http: Http) {
    }

    // 获取所有运营人员
    getUsers(params): Observable<any[]> {
        return this.http.get(ROOT_URL + '/sys/admin/list', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 获取所有角色
    createUsers(params): Observable<any[]> {
        return this.http.get(ROOT_URL + 'manage/roles/list', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 编辑用户显示 指定资源
    readUsers(params): Observable<any[]> {
        return this.http.get(ROOT_URL + 'manage/admin/detail', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 删除用户
    deleteUsers(params): Observable<any[]> {
        return this.http.post(ROOT_URL + '/sys/admin/delete', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 添加用户
    saveUsers(params): Observable<any[]> {
        return this.http.post(ROOT_URL + '/sys/admin/add', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 重置密码
    resetUsers(params): Observable<any[]> {
        return this.http.post(ROOT_URL + '/sys/admin/reset', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 编辑用户信息
    updateUsers(params): Observable<any[]> {
        return this.http.post(ROOT_URL + '/sys/admin/add', params)
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
        return Observable.throw(errMsg);
    }
}
