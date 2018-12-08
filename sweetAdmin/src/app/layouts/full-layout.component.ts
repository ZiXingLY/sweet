import {Component, OnInit} from '@angular/core';
import {Http} from '@angular/http';
import {NavigationEnd, Router} from '@angular/router';

@Component({
    selector: 'app-dashboard',
    templateUrl: './full-layout.component.html'
})
export class FullLayoutComponent implements OnInit {
    public userName: any = localStorage.userName;
    public disabled = false;
    public status: { isopen: boolean } = {isopen: false};
    public permissions: any = localStorage.permissions ? JSON.parse(localStorage.permissions) : [];
    public isShowMsgPoint: any = false;
    public url: string;
    public message: any;
    // public isShowMsgPoint: any;

    // public page: String = '1';

    public toggled(open: boolean): void {
    }

    public toggleDropdown($event: MouseEvent): void {
        $event.preventDefault();
        $event.stopPropagation();
        this.status.isopen = !this.status.isopen;
    }

    constructor(public http: Http, public router: Router) {
    };

    ngOnInit(): void {
        // this.router.events.subscribe((event) => {
        //     if (event instanceof NavigationEnd) {
        //         // if (event['url'] === '/message/index') {
        //         //     this.isShowMsgPoint = false;
        //         //     return;
        //         // }
        //         const params: any = {
        //             // pageNum: 1,
        //         };
        //         this.http.get(ROOT_URL + 'manage/message/messageManage', {params}).subscribe((result) => {
        //             result = result.json();
        //             console.log(result);
        //             if (result['code'] === 0) {
        //                 this.message = result['obj'];
        //                 // this.message = localStorage.message;
        //                 if (this.message > 0) {
        //                     this.isShowMsgPoint = true
        //
        //                 } else {
        //                     this.isShowMsgPoint = false;
        //
        //                 }
        //             }
        //             // console.log(localStorage.message);
        //             // for (let i = 0; i < result['data']['list']['length']; i++) {
        //             //     if (result['data']['list'][i]['message_state'] === 1) {
        //             //         this.isShowMsgPoint = true;
        //             //         this.message = this.message + 1;
        //             //         // return;
        //             //     }
        //             // }
        //             // if (this.message === 0) {
        //             //     this.isShowMsgPoint = false;
        //             // }
        //             // if (result['data']['total'])
        //         });
        //
        //     }
        // });
        // this.url = '/' + JSON.parse(localStorage.permissions)[0]['controller'] + '/index';
        // this.router.events.subscribe((event) => {
        //     if (event instanceof NavigationEnd) {
        //         if (event['url'] === '/message/index') {
        //             this.isShowMsgPoint = false;
        //             return;
        //         }
        //         const params: any = {
        //             token: localStorage.token,
        //         };
        //         this.http.get(ROOT_URL + 'Message/index', {params}).subscribe((result) => {
        //
        //             result = result.json();
        //             if (localStorage.msg) {
        //                 if (localStorage.msg !== JSON.stringify(result['data']['data'])) {
        //                     this.isShowMsgPoint = true;
        //
        //                 }else {
        //                     this.isShowMsgPoint = false;
        //                 }
        //             }else {
        //                 if (result['data']['data']) {
        //                     this.isShowMsgPoint = true;
        //                 }
        //             }
        //         });
        //     }
        // });
    }

    checkPermissions(str) {
        let isHas = false;
        this.permissions.map((value, index) => {
            if (str === value['controller']) {
                isHas = true;
            }
        });
        // return isHas;
        return true;
    }

    signOut() {
        localStorage.permissions = '';
        localStorage.token = '';
        localStorage.tokenTime = '';
        idType = '';
        loginId = '';
        window.location.href = window.location.href.split('#')[0] + '#/login';
        window.location.reload();
    }
}
