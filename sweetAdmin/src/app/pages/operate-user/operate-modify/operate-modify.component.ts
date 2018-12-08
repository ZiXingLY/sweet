import {Component, OnInit} from '@angular/core';
import {OperateSericve} from '../operate-user.service';
import {Router, ActivatedRoute, Params} from '@angular/router';
import {LoadingService} from '../../../shared/loading.service';
import {AlertService} from '../../../shared/alert.service';
import {Md5} from 'ts-md5/dist/md5';

@Component({
    selector: 'app-operate-modify',
    templateUrl: './operate-modify.component.html',
    styleUrls: ['./operate-modify.component.scss']
})
export class OperateModifyComponent implements OnInit {
    public transmissionId: number;
    // 判断还修改还是添加
    public issuccess: Boolean = false;
    public isModalShown: Boolean = false;
    // 提示语
    public modelBody: string;
    // 保存用户角色信息
    public userRoles: Array<any> = [];
    // 绑定添加数据
    public AdministrationData = {
        number: '',
        name: '',
        role: 1,
        confirmPassword: '',
        password: '',
        account: ''
    };
    public isedit: Boolean;  //  判断是否编辑

    constructor(private route: ActivatedRoute,
                private router: Router,
                private service: OperateSericve,
                public loading: LoadingService,
                public alert: AlertService) {
    }

    ngOnInit() {
        // this.route.params.subscribe(params => {
        //     this.transmissionId = params.id;
        // });
        // if (this.transmissionId) {
        //     const params: any = {
        //         id: this.transmissionId,
        //     };
        //     this.service.readUsers(params).subscribe(
        //         res => {
        //             this.AdministrationData.account = res['data']['account'];
        //             this.AdministrationData.name = res['data']['real_name'];
        //             this.AdministrationData.role = res['data']['role_id'];
        //             this.isedit = false;
        //         },
        //         error => {
        //             this.loading.hide();
        //             this.alert.show(error['message']);
        //         }
        //     );
        // }
        // const params: any = {};
        // this.service.createUsers(params).subscribe(
        //     res => {
        //         this.userRoles = res['data']['list'];
        //         this.loading.hide();
        //     },
        //     error => {
        //         this.loading.hide();
        //         this.alert.show('网络错误');
        //     }
        // );
    }


    //   确定添加或者编辑
    determine() {
        this.loading.show();
        // if (this.AdministrationData.name === '') {
        //     this.isModalShown = true;
        //     this.modelBody = '姓名不能为空';
        //     this.issuccess = false;
        // } else
        if (this.AdministrationData.number === '' && !this.transmissionId ) {
            this.isModalShown = true;
            this.modelBody = '账号不能为空';
            this.issuccess = false;
        } else if (this.AdministrationData.password === '' && !this.transmissionId) {
            this.isModalShown = true;
            this.modelBody = '请输入密码';
            this.issuccess = false;
        } else if (this.AdministrationData.password !== this.AdministrationData.confirmPassword && !this.transmissionId) {
            this.isModalShown = true;
            this.modelBody = '两次密码不一致';
            this.issuccess = false;
        } else if (!this.transmissionId && this.AdministrationData.password.length < 6 || this.AdministrationData.password.length > 20) {
            this.isModalShown = true;
            this.modelBody = '请输入6~20位的密码';
            this.issuccess = false;
        } else {
            if (this.transmissionId !== undefined) {
                const params: any = {
                    account: this.AdministrationData.account,
                    real_name: this.AdministrationData.name,
                    // id: this.transmissionId,
                };
                this.service.updateUsers(params).subscribe(
                    res => {
                        if (res['code'] === 0) {
                            this.isModalShown = true;
                            this.modelBody = '编辑成功';
                            this.issuccess = true;
                        } else {
                            this.isModalShown = true;
                            this.modelBody = res['msg'];
                            this.issuccess = false;
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
            } else {
                const params: any = {
                    account: this.AdministrationData.number,
                    real_name: this.AdministrationData.name,
                    password: this.AdministrationData.password,
                };
                this.service.saveUsers(params).subscribe(
                    res => {
                        if (res['code'] === 0) {
                            this.isModalShown = true;
                            this.modelBody = '添加成功';
                            this.issuccess = true;
                        } else {
                            this.isModalShown = true;
                            this.modelBody = res['msg'];
                            this.issuccess = false;
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
        }
    }

    confirmshutdown() {
        this.loading.show();
        this.isModalShown = false;
        if (this.issuccess === true) {
            window.history.go(-1);
        }
        this.loading.hide();
    }

    returnHistory() {
        window.history.go(-1);
    }

}
