import { Component, OnInit } from '@angular/core';
import {FlashService} from '../flash.service'
import {AlertService} from '../../../shared/alert.service'
import {LoadingService} from '../../../shared/loading.service'
@Component({
  selector: 'app-flash-list',
  templateUrl: './flash-list.component.html',
  styleUrls: ['./flash-list.component.scss']
})
export class FlashListComponent implements OnInit {
    public IMG_URL = IMG_URL;
    public showkeys: any = 1; // 1昵称  2 账号
    public isNodata: Boolean = false;
    public dataList: any = [];

    public delectId: any = ''; // 操作id
    public modalText: any; // 操作提示语
    public modelBody: any; // 操作完成提示语
    public delectModal: Boolean = false; // 提示样式

    public nickname: any = ''; // 昵称
    public account: any = ''; // 账号
    public user_state: any = ''; // 1禁用 2未禁用
    public user_type: any = '';  // 1普通用户 2基地会员

    public auditModal: Boolean = false;
    public issuccess: Boolean = false;
    public auditData: any = []; // 审核获取的数据

    // 分页
    public page: any = 1;
    public totalItems: any;   // 列表数据总数
    public currentPage: any = 1;   // 默认的开始页数
    public maxSize: Number = 3;    // 显示的最大按钮数量


    constructor(public service: FlashService,
                public alert: AlertService,
                public loading: LoadingService) {
    }

    ngOnInit() {
        this.getDate();
    }

    showKey() {
        this.account = '';
        this.nickname = '';
    }


    // 获取用户列表
    getDate() {
        this.loading.show();
        const params: any = {
            // nickname: this.nickname,
            // account: this.account,
            // user_type: this.user_type,
            // user_state: this.user_state,
            pageNum: this.page
            // pageSize: 10
        };
        console.log(params);
        this.service.flashList(params).subscribe(
            res => {
                console.log(res);
                    this.loading.hide();
                    this.dataList = [];
                    if (res['list'] === '') {
                        this.isNodata = true;
                    } else {
                        this.isNodata = false;
                        this.dataList = res['data'];
                        // for (let i = 0; i < this.dataList.length; i++) {
                        //     if (this.dataList[i]['head_picture'] !== null) {
                        //         this.dataList[i]['head_picture'] = JSON.parse(this.dataList[i]['head_picture'])[0];
                        //     }
                        // }
                        // this.dataList.map((v) => {
                        //     if (v['head_picture'].indexOf('[') !== -1) {
                        //         v['head_picture'] = JSON.parse(v['head_picture'])[0];
                        //     }
                        // });
                        this.totalItems = res['total'];

                    }
            }, error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        )
    }

    // 禁用提示
    tipshow(id, type) {
        this.delectId = id;
        if (type === 1) {
            this.modalText = '确定要禁用？'
        } else {
            this.modalText = '确定取消禁用？';
        }
    }


    determination() {
        this.loading.show();
        // const params: any = {
        //     id: this.delectId
        // }
        this.service.flashList({id: this.delectId}).subscribe(
            res => {
                setTimeout(() => {
                    console.log(res)
                    this.loading.hide();
                }, 300);
                if (res['code'] === 0) {
                    this.modelBody = '';
                    this.delectModal = true;
                    this.issuccess = true;
                    this.modelBody = '操作成功';

                } else {
                    this.modelBody = '禁用失败';
                    this.delectModal = true;
                    this.issuccess = false;
                }
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
    }

    auditModules() {
        this.auditModal = false;
    }


    // 切换分页进行网络请求
    pageChanged(event: any): void {
        this.page = event.page;
        this.loading.show();
        this.getDate();
    }

    // 操作完成确定
    confirmshutdown() {
        this.auditModal = false;
        this.delectModal = false;
        this.getDate();
    }
}
