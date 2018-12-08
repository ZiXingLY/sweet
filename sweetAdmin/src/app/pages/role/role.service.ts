/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class RoleService {
    constructor(private http: Http) {
    }

    // 获取列表
    getRoles(params): Observable<any[]> {
        return this.http.get(ROOT_URL + 'manage/roles/list', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 添加数据 添加角色
    saveRoles(params): Observable<any[]> {
        return this.http.post(ROOT_URL + 'manage/roles/add', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 删除角色
    deleteRoles(params): Observable<any[]> {
        return this.http.post(ROOT_URL + 'manage/roles/delete', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 获取选择的权限信息
    oneAuthorityRoles(params): Observable<any[]> {
        return this.http.get(ROOT_URL + 'manage/admin/permissionsList', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 添加用户权限
    operationRoles(params): Observable<any[]> {
        return this.http.post(ROOT_URL + 'manage/admin/configPermission', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 获取所有权限
    authorityRoles(params): Observable<any[]> {
        return this.http.get(ROOT_URL + 'manage/admin/permissionsList', {params})
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
