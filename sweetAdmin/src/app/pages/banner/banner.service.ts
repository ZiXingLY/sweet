/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class BannerService {
    constructor(private http: Http) {
    }

    // 获取轮播图列表
    bannerList(params): Observable<any> {
        return this.http.get(ROOT_URL + 'manage/banner/list', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 上架轮播图
    bannerUpShelves(params): Observable<any> {
        return this.http.post(ROOT_URL + 'manage/banner/shelf', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 下架轮播图
    bannerDownShelves(params): Observable<any> {
        return this.http.post(ROOT_URL + 'manage/banner/lower', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 删除轮播图
    bannerDeletes(params): Observable<any> {
        return this.http.post(ROOT_URL + 'manage/banner/delete', params)
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 新增轮播图
    addNewBanner(params): Observable<any> {
        return this.http.post(ROOT_URL + 'manage/banner/addBanner', params)
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
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
}
