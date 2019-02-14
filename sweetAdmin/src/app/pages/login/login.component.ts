import {Component} from '@angular/core';
import {Http} from '@angular/http';
import {Router} from '@angular/router';
import {LoadingService} from '../../shared/loading.service';
import {Md5} from 'ts-md5/dist/md5';

@Component({
    templateUrl: 'login.component.html'
})
export class LoginComponent {
    public userName: any;
    public passWord: any;
    public aname: any;

    constructor(public http: Http, private router: Router, private loading: LoadingService) {
    }

    register() {
        const params = {
            account: this.userName,
            password: this.passWord,
            name: this.aname
        };
        this.loading.show();
        this.http.post(ROOT_URL + 'sys/admin/add', params).subscribe(res => {
            console.log(res);
            if (res['code'] === 0) {
                this.loading.hide();
                console.log(res);
            }
        })
    }

    login() {
        if (this.userName === undefined && this.passWord === undefined) {
            alert('账号/密码不能为空');
        } else if (this.userName === '' && this.passWord === '') {
            alert('账号/密码不能为空');
        } else if (this.userName === undefined || this.userName === '') {
            alert('请输入用户名');
        } else if (this.passWord === undefined || this.passWord === '') {
            alert('请输入密码');
        } else {
            const params = {
                account: this.userName,
                password: this.passWord
            };
            this.loading.show();
            this.http.get(ROOT_URL + 'sys/admin/login', {params}).subscribe((result) => {
                this.loading.hide();
                result = result.json();
                if (result['code'] === 0) {
                    localStorage.userName = this.userName;
                    // localStorage.tokenTime = result['admin']['time'];
                    localStorage.id = result['data']['admin']['id'];
                    localStorage.idType = result['data']['admin']['roles_id'];
                    // localStorage.permissions = JSON.stringify(result['data']['permissions']);
                    localStorage.permissions = JSON.stringify(result['data']['permissions']);
                    console.log(localStorage.permissions);
                    if (result['data']['permissions'][0].controller) {
                        this.router.navigateByUrl(result['data']['permissions'][0].controller ? (result['data']['permissions'][0].controller + '/index') : 'login');
                    } else {
                        alert('页面好像丢了');
                    }
                } else {
                    alert(result['message']);
                }
            });
        }
    }
}
