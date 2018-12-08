import {Component, OnInit} from '@angular/core';
// import {OperateMessageService} from '../message.service';
import {MessageService} from '../message.service';
import {AlertService} from '../../../shared/alert.service';
import {LoadingService} from '../../../shared/loading.service';

@Component({
    selector: 'app-message-list',
    templateUrl: './message-list.component.html',
    styleUrls: ['./message-list.component.scss']
})
export class MessageListComponent implements OnInit {
    public dataArr: any = [];
    public page: Number = 1;
    public state: String = '';
    public isNodata: Boolean = false;
    public totalItems: any; // 列表数据总数
    public currentPage: any = 1; // 默认的开始页数
    public maxSize: Number = 3; // 显示的最大按钮数量
    // 第一个确定的文字
    public modalText: String = '你确定要删除选定的消息吗？';
    // 第二个确定的文字
    public successText: string;
    public dangerModal: Boolean = false;
    // 弹出框的提示模态
    public issuccess: Boolean = false;
    public selecTed: Boolean = false;
    public master: Boolean = false;
    public choseArr: Array<any> = [];
    public choseArrstring: string;
    public selectAll: Boolean = false;


    constructor(public service: MessageService,
                public alert: AlertService,
                public loading: LoadingService) {
    }

    ngOnInit() {
        this.loading.show();
        this.getData();

    }

    pageChanged(event) {
        this.page = event.page;
        this.loading.show();
        this.getData();

    }

    getData() {
        const params: any = {
            pageNum: this.page,
        };
        this.service.getList(params).subscribe(
            res => {
                if (res['code'] === 0) {
                    if (res['data']['list']['length'] === 0) {
                        this.isNodata = true;
                        this.dataArr = [];
                    } else {
                        this.isNodata = false;
                        this.dataArr = res['data']['list'];
                        this.totalItems = res['data']['total'];
                        // for (let i = 0; i < this.dataArr.length; i++) {
                        //     this.dataArr[i]['add_time'] = new Date(parseInt(this.dataArr[i]['add_time'])).toLocaleString().replace(/年|月/g, '-').replace(/日/g, ' ');
                        // }
                    }
                    setTimeout(() => {
                        this.loading.hide();
                    }, 300);
                }
            },
            error => {
                setTimeout(() => {
                    this.loading.hide();
                }, 300);
                this.alert.show('网络错误');
            }
        );

    }

    all(master) {
        this.master = !master;
        if (this.master) {
            this.choseArr = [];
            this.selecTed = true;
            for (let i = 0; i < this.dataArr.length; i++) {
                this.choseArr.push(this.dataArr[i]['id']);
            }
        } else {
            this.selecTed = false;
            this.choseArr = [];
        }
        this.selectAll = true;
    }

    choice(id, chack) {
        // console.log(this.choseArr);
        // console.log(this.test[id]);
        if (chack) {
            this.choseArr.push(id);
        } else {
            for (let i = 0; i < this.choseArr.length; i++) {
                if (this.choseArr[i] === id) {
                    this.choseArr.splice(i, 1);
                }
            }
            // console.log(this.choseArr);
        }
        if (this.choseArr.length === this.dataArr.length) {
            this.master = true;
        } else {
            this.master = false;
        }
    }

    deleteSelected() {
        this.choseArrstring = JSON.stringify(this.choseArr);
        this.choseArrstring = this.choseArrstring.substring(1, this.choseArrstring.length - 1);
        this.loading.show()
        const params: any = {
            ids: this.choseArrstring
        };
        this.service.deleteMessage(params).subscribe(
            res => {
                if (res['code'] === 0) {
                    this.dangerModal = true;
                    this.issuccess = true;
                    this.successText = '删除成功';
                } else {
                    this.dangerModal = true;
                    this.issuccess = false;
                    this.successText = res['msg'];
                }
                this.loading.hide();
                this.choseArr = [];
                this.selecTed = false;
                this.master = false;
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
    }

    hideModal() {
        this.dangerModal = false;
        if (this.issuccess === true) {
            this.loading.show();
            this.getData()
            this.choseArr = [];
            this.selecTed = false;
        }
    }

    read(id) {
        this.loading.show();
        const params: any = {
            id: id
        };
        this.service.readMessage(params).subscribe(
            res => {
                console.log(res);
                if (res['code'] === 0) {
                    this.dangerModal = true;
                    this.issuccess = true;
                    this.successText = '已阅读';
                    // this.message();

                } else {
                    this.dangerModal = true;
                    this.issuccess = false;
                    this.successText = res['msg'];
                }
                this.loading.hide();
                this.choseArr = [];
                this.selecTed = false;
                this.master = false;
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
    }

    // message() {
    //     const params: any = {
    //         // pageNum: 1,
    //     };
    //     this.service.getMessage(params).subscribe((result) => {
    //             // result = result.json();
    //             console.log(result);
    //             if (result['code'] === 0) {
    //                 localStorage.message = result['obj'];
    //                 if (localStorage.message > 0) {
    //                     localStorage.isShowMsgPoint = true
    //
    //                 } else {
    //                     localStorage.isShowMsgPoint = false;
    //
    //                 }
    //             }
    //         },
    //         error => {
    //             this.loading.hide();
    //             this.alert.show('网络错误');
    //         }
    //     );
    // }

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
    }
    ;


}
