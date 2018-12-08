import {Component, OnInit} from '@angular/core';
import {NavigationStart, Router} from '@angular/router';
import {LoadingService} from './shared/loading.service';

@Component({
    // tslint:disable-next-line
    selector: 'body',
    template: '<router-outlet></router-outlet>'
})
export class AppComponent implements OnInit {
    constructor(private router: Router, private loadingService: LoadingService) {
    }
    ngOnInit() {
        // const nowTime: any = new Date().getTime().toString().substr(0, 10);
        // if (!localStorage.token || !localStorage.tokenTime || ((nowTime - 0) - (localStorage.tokenTime - 0)) > 43200) {
        //     this.router.navigateByUrl('/login');
        // }
        // this.router.events.subscribe((event) => {
        //     if (event instanceof NavigationStart) {
        //         if (this.loadingService.isShow) {
        //             this.loadingService.hide();
        //         }
        //     }
        // });
    }
}

