import {Component, OnInit} from '@angular/core';
import {PasswordModifyService} from '../password-modify.service';
import {LoadingService} from '../../../shared/loading.service';
import {AlertService} from '../../../shared/alert.service';
import {Md5} from 'ts-md5/dist/md5';

@Component({
    selector: 'app-modify-page',
    templateUrl: './modify-page.component.html',
    styleUrls: ['./modify-page.component.scss']
})
export class ModifyPageComponent implements OnInit {
    public delectModal: Boolean = false;
    public issuccess: Boolean = false;
    public modelBody: string;
    // 保存填写内容
    public modificationInformation: any = {
        oldPassword: '',
        newPassword: '',
        repassword: '',
    };
    // 提示语控制
    public Isidentical: Boolean = false;
    public validationHints: Boolean = false;

    constructor(public loading: LoadingService,
                private service: PasswordModifyService,
                public alert: AlertService,) {
    }

    // 新旧密码验证
    authenTication() {
        if (this.modificationInformation.newPassword === this.modificationInformation.oldPassword) {
            this.Isidentical = true;
        } else {
            this.Isidentical = false;
        }
    }

    // 两次新密码验证
    inputValidation() {
        if (this.modificationInformation.newPassword === this.modificationInformation.repassword) {
            this.validationHints = false;
        } else {
            this.validationHints = true;
        }
    }

    ngOnInit() {
    }

    modifySubmit() {
        if (this.modificationInformation.newPassword === this.modificationInformation.oldPassword) {
            this.alert.show('新密码和旧密码不能一致');
        } else if (this.modificationInformation.newPassword === this.modificationInformation.repassword) {
            this.loading.show();
            const params: any = {
                id: localStorage.id,
                oldPassword: this.modificationInformation.oldPassword,
                newPassword: this.modificationInformation.newPassword,
                // confirm: this.modificationInformation.repassword,
            };
            this.service.modifyPassword(params).subscribe(
                res => {
                    this.loading.hide();
                    if (res['code'] === 0) {
                        this.alert.show('修改成功');
                        setTimeout(() => {
                            localStorage.permissions = '';
                            localStorage.token = '';
                            localStorage.tokenTime = '';
                            window.location.href = window.location.href.split('#')[0] + '#/login';
                            window.location.reload();
                        }, 3000);
                    } else {
                        this.alert.show(res['message'])
                    }
                },
                error => {
                    this.loading.hide();
                    this.loading.hide();
                    this.alert.show('网络错误');
                }
            );
        } else {
            this.alert.show('两次密码不一致');
        }
    }

    confirmshutdown() {
        this.delectModal = false;
    }
}
