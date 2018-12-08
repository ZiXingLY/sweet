import {Component, OnInit} from '@angular/core';
import {AlertService} from '../../../shared/alert.service';
import {LoadingService} from '../../../shared/loading.service';
import {ArticleService} from '../article.service'
import {el} from '@angular/platform-browser/testing/src/browser_util';

@Component({
    selector: 'app-article-list',
    templateUrl: './article-list.component.html',
    styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent implements OnInit {
    public imgurl = IMG_URL;
    public banner: any;
    public requestData: any = {
        // type: '1',		// 类型 1政策名称 2发布人	展开
        // content: '',	// 输入框内容
        // policy_type: '',		// 政策类型 （类型id
        state: '',		 // 审核状态 1待审核 2通过 3不通过
        // state: '',		// 状态 1上架 2下架
        pageNum: 1,
    };
    // 提示模态框
    public dangerModal: Boolean = false;
    public successText: string;
    public dataArr: Array<any> = [];
    public policyType: Array<any> = [];
    public optionId: any;
    public optionType: any;
    public optionState: any;
    // 分页组件相关配置
    public page: any = 1;
    public isnoData: Boolean = false;
    public totalItems: any; // 列表数据总数
    public currentPage: any = 1; // 默认的开始页数
    public maxSize: Number = 3; // 显示的最大按钮数量
    constructor(public alert: AlertService, public loading: LoadingService, public service: ArticleService) {
    }

    ngOnInit() {
        this.loading.show();
        this.getData();

    }
    // 获取文章列表
    getData() {
        this.service.getList(this.requestData).subscribe(
            res => {
                console.log(res);

                this.loading.hide();
                if (res['code'] === 0) {
                    if (res['data']['list'].length === 0) {
                        this.isnoData = true;
                        this.dataArr = [];
                    } else {
                        this.dataArr = res['data']['list'];
                        this.isnoData = false;
                        this.totalItems = res['data']['total']
                    }
                }
            },
            error => {
                this.loading.hide();
            }
        );
    }

    sqlBase() {
        this.loading.show();
        this.currentPage = 1;
        this.requestData.pageNum = 1;
        this.getData();
    }

    deleteData(id) {
        this.optionId = id;
        this.successText = '确定删除该文章？';
        this.optionState = 1;
        this.dangerModal = true;
    }

    shelfState(id, type) {
        this.optionState = 2;
        this.optionId = id;
        this.optionType = type;
        if (type === 1) {
            this.successText = '确定上架该文章?';
        } else {
            this.successText = '确定下架该文章?';
        }
        this.dangerModal = true;
    }
    setto(id, type) {
        this.optionState = 3;

        this.banner = id;
        if (type == 1) {
            this.successText = '确定上架该文章为轮播图?';
        }else {
            this.successText = '确定下架该文章为轮播图?';
        }
        this.dangerModal = true;


    }

    hideModal(type) {
        this.dangerModal = false;
        if (type === 1) {
            if (this.optionState === 1) {
                this.loading.show();
                const parmas: any = {
                    aid: JSON.stringify(this.optionId),
                };
                this.service.delete(parmas).subscribe(
                    res => {
                        this.loading.hide();
                        if (res['code'] === 0) {
                            this.alert.show('删除成功');
                            this.getData();
                        } else {
                            this.alert.show(res['message']);
                        }
                    },
                    error => {
                        this.loading.hide();
                        this.alert.show('服务器繁忙，请联系管理员');
                    }
                );
            } else if (this.optionState === 2) {
                this.loading.show();
                const params: any = {
                    state: this.optionType,
                    id: this.optionId
                };
                this.service.policyState(params).subscribe(
                    res => {
                        this.loading.hide();
                        if (res['code'] === 0) {
                            if (this.optionType === 1) {
                                this.alert.show('上架成功');
                            } else {
                                this.alert.show('下架成功');
                            }
                            this.getData();
                        } else {
                            this.alert.show(res['message']);
                        }
                    },
                    error => {
                        this.loading.hide();
                        this.alert.show('服务器内部错误，请联系管理员')
                    }
                );
            }else if (this.optionState === 3) {
                this.settobanner();


            }
        }
    }
    // 设置轮播图
    settobanner() {
        const parmas: any = {
            id: this.banner,
        };
        this.service.settobanner(parmas).subscribe(
            res => {
                if (res['bool'] === true) {
                        this.alert.show('成功');
                    this.getData();
                } else {
                    this.alert.show(res['message']);
                }
    },
      error => {
          this.loading.hide();
          this.alert.show('服务器内部错误，请联系管理员')
      })

    }

    pageChanged(event) {
        this.requestData.pageNum = event.page;
        this.loading.show();
        this.getData();
    }

    formatDateTime(inputTime) {
        const time = new Date(parseInt(inputTime));
        const y = time.getFullYear();
        const m = time.getMonth() + 1;
        const min = m < 10 ? ('0' + m) : m;
        const d = time.getDate();
        const day = d < 10 ? ('0' + d) : d;
        const h = time.getHours();
        const hours = h < 10 ? ('0' + h) : h;
        const minute = time.getMinutes();
        const second = time.getSeconds();
        const minutes = minute < 10 ? ('0' + minute) : minute;
        const seconds = second < 10 ? ('0' + second) : second;
        return y + '-' + min + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
    };
    getDatadata() {
        const parmas: any = {
        };
        this.service.getDatadata(parmas).subscribe(
            res => {
                // if (res['bool'] === true) {
                    this.alert.show('成功');
                    // this.getData();
                // } else {
                    // console.log(res)
                    // this.alert.show(res['message']);
                // }
            },
            error => {
                this.loading.hide();
                this.alert.show('服务器内部错误，请联系管理员')
            })

    }
}
