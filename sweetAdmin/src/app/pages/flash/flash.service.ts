/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class FlashService {
    constructor(private http: Http) {
    }

    // 获取轮播图列表
    flashList(params): Observable<any> {
        return this.http.get(ROOT_URL + '/flash/sysList', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
    seePolicy(params): Observable<any> {
        return this.http.get(ROOT_URL + 'flash/detail', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 标签列表
    labelList(params): Observable<any> {
        return this.http.get(ROOT_URL + '/category/getCategory')
            .map(this.extractData)
            .catch(this.handleError);
    }

    // 上架轮播图
    popstflash(params): Observable<any> {
        return this.http.post(ROOT_URL + '/flash/sysUploadFlash', params)
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
        return Observable.throw(errMsg);
    }
}
