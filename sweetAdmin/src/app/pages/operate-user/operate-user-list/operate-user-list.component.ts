import {Component, OnInit} from '@angular/core';
import {OperateSericve} from '../operate-user.service';
import {LoadingService} from '../../../shared/loading.service';
import {AlertService} from '../../../shared/alert.service';
import {Md5} from 'ts-md5/dist/md5';

@Component({
    selector: 'app-operate-user-list',
    templateUrl: './operate-user-list.component.html',
    styleUrls: ['./operate-user-list.component.scss']
})
export class OperateUserListComponent implements OnInit {
    public accountArr: Array<any> = [];
    public loginIds: any; // 登陆id
    public idTypes: any; //  登陆账号类型
    // 查询数据
    public conditionsData = {
        account: '',
        real_name: '',
        key: 2
    };
    // 保存第几页
    public nowpages: Number = 1;
    public isNodata: Boolean = false;
    public totalItems: any; // 列表数据总数
    public currentPage: any = 1; // 默认的开始页数
    public maxSize: Number = 3; // 显示的最大按钮数量
    public delectId: number;
    // 区分是删除还是重置密码
    public isdelect: Boolean = false;
    // 模态框
    public modalText: string;
    public delectModal: Boolean = false;
    public issuccess: Boolean = false;
    public modelBody: string;
    //   保存密码
    public password: string;
    public resetPasswords: Boolean = false;

    public newPassword: any = ''; // 新密码
    public okPassword: any = ''; // 确认新密码
    public oldPassword: any = '';

    constructor(public loading: LoadingService,
                private service: OperateSericve,
                public alert: AlertService) {
    }

    ngOnInit() {
        this.loginIds = localStorage.id;
        this.idTypes = localStorage.idType;
        this.getdata();
    }

    // 获取数据
    getdata() {
        this.loading.show();
        const params: any = {
            account: this.conditionsData.account,
            real_name: this.conditionsData.real_name,
            pageNum: this.nowpages,
        };
        this.service.getUsers(params).subscribe(
            res => {
                if (res['data']['list']['length'] == 0) {
                    this.isNodata = true;
                    this.accountArr = [];
                } else {
                    this.isNodata = false;
                    this.accountArr = res['data']['list'];
                    this.totalItems = res['data']['total'];

                }
                setTimeout(() => {
                    this.loading.hide();
                }, 300);
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
    }

    // 删除按钮
    deleteUser(id: number) {
        this.delectId = id;
        this.modalText = '确定要删除';
        this.isdelect = true;
    }

    // 重置密码
    resetPassword(id: number) {
        this.delectId = id;
        this.modalText = '确定要重置密码';
        this.isdelect = false;
    }

    determination() {
        this.loading.show();
        if (this.isdelect === true) {  // 删除用户
            const params: any = {
                id: this.delectId
            };
            this.service.deleteUsers(params).subscribe(
                res => {
                    setTimeout(() => {
                        this.loading.hide();
                    }, 300);

                    this.getdata();
                    // if (res['code'] === 0) {
                    //     this.modelBody = '删除成功';
                    //     this.delectModal = true;
                    //     this.issuccess = true;
                    // } else {
                    //     this.modelBody = '删除失败';
                    //     this.delectModal = true;
                    //     this.issuccess = false;
                    // }
                },
                error => {
                    this.loading.hide();
                    this.alert.show('网络错误');
                }
            );
        }
        if (this.isdelect === false) {
            this.resetPasswords = true;
            this.loading.hide();
        }
    }

    pageChanged(event) {
        this.loading.show();
        this.nowpages = event.page;
        const params: any = {
            pageNum: event.page,
        };
        this.service.getUsers(params).subscribe(
            res => {
                if (res['data']['length'] === 0) {
                    this.isNodata = true;
                    this.accountArr = [];
                } else {
                    this.isNodata = false;
                    this.accountArr = res['data']['list'];
                    this.totalItems = res['data']['total'];
                }
                setTimeout(() => {
                    this.loading.hide();
                }, 300);
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
    }

    confirmshutdown() {
        this.delectModal = false;
        this.loading.show();
        if (this.issuccess === true && this.isdelect === true) {
            const params: any = {
                pageNum: this.nowpages,
            };
            this.service.getUsers(params).subscribe(
                res => {
                    if (res['data']['length'] === 0) {
                        this.isNodata = true;
                        this.accountArr = [];
                    } else {
                        this.accountArr = res['data']['list'];
                        this.totalItems = res['data']['total'];
                    }
                    setTimeout(() => {
                        this.loading.hide();
                    }, 300);
                },
                error => {
                    this.loading.hide();
                    this.alert.show('网络错误');
                }
            );
        }
        this.loading.hide();
    }

//     取消重密码
    cancelPassword() {
        this.resetPasswords = false;
        // const params: any = {
        //     id: this.delectId,
        // };
        // this.service.resetUsers(params).subscribe(
        //     res => {
        //         setTimeout(() => {
        //             this.loading.hide();
        //         }, 300);
        //         if (res['code'] === 0) {
        //             this.modelBody = '密码重置成功';
        //             this.delectModal = true;
        //             this.issuccess = true;
        //         } else {
        //             this.modelBody = '密码重置失败';
        //             this.delectModal = true;
        //             this.issuccess = false;
        //         }
        //     },
        //     error => {
        //         this.loading.hide();
        //         this.alert.show('网络错误');
        //     }
        // );
    }


    // 重置密码
    confirmResetPassword() {
        if (this.newPassword == '' || this.okPassword == '') {
            this.alert.show('请输入密码/确认密码');
            this.issuccess = false;
            return;
        }
        if (this.newPassword !== this.okPassword) {
            this.alert.show('两次密码不一致');
            this.issuccess = false;
            return;
        }
        if (this.newPassword.length < 6 || this.newPassword.length > 20) {
            this.alert.show('请输入6~20位的密码');
            this.issuccess = false;
            return;
        }
        const params: any = {
            id: this.delectId,
            password: this.newPassword,
        };
        this.service.resetUsers(params).subscribe(
            res => {
                if (res['code'] == 0) {
                    this.resetPasswords = false;
                    this.alert.show('密码重置成功');
                    this.loading.hide();
                    setTimeout(() => {
                        window.location.href = window.location.href.split('#')[0] + '#/login';
                    })

                } else {
                    this.alert.show(res['message']);
                }
            }, error => {
                this.alert.show('网络错误');
            }
        );


    }


}
