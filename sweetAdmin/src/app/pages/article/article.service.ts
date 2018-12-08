/**
 * Created by zaq on 2017/5/12.
 */
import {Injectable} from '@angular/core';
import {Http, Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';


@Injectable()
export class ArticleService {
    constructor(private http: Http) {
    }
//    查询
    getDatadata(params): Observable<any> {
        return this.http.get(ROOT_URL + '/sysFindArticleByState', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }

//    轮播图
    settobanner(params): Observable<any> {
        return this.http.get(ROOT_URL + '/front/article/setBanner', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
// 获取列表
    getList(params): Observable<any> {
        return this.http.get(ROOT_URL + '/article/sysFindArticleByState', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 点击政策管理
    polocyList(params): Observable<any> {
        return this.http.get(ROOT_URL + 'manage/policy/clickPolocy', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
     // 查看政策
    seePolicy(params): Observable<any> {
        return this.http.get(ROOT_URL + '/article/sysReadArticle', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 删除政策
    delete(params): Observable<any> {
        return this.http.post(ROOT_URL + 'article/delete', params)
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 政策上下架
    policyState(params): Observable<any> {
        return this.http.get(ROOT_URL + '/article/sysChangeArticleState', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 标签列表
    labelList(params): Observable<any> {
        return this.http.get(ROOT_URL + '/category/getCategory')
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 基地列表
    baseId(params): Observable<any> {
        return this.http.get(ROOT_URL + 'manage/base/baseId', {params})
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 新增政策
    publish(params): Observable<any> {
        return this.http.post(ROOT_URL + 'manage/policy/publish', params)
            .map(this.extractData)
            .catch(this.handleError);
    }
    // 编辑政策
    edit(params): Observable<any> {
        return this.http.post(ROOT_URL + '/article/sysUploadArticle', params)
            .map(this.extractData)
            .catch(this.handleError);
    }
    //  政策审核
    policyCheck(params): Observable<any> {
        return this.http.get(ROOT_URL + '/article/sysChangeArticleState', {params})
            .map(this.extractData)
            .catch(this.handleError);

    }
    private extractData(res: Response) {
        const body = res.json();
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
