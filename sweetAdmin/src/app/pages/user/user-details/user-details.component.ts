import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service'
import {AlertService} from '../../../shared/alert.service'
import {LoadingService} from '../../../shared/loading.service'
import {ActivatedRoute} from '@angular/router'
@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.scss']
})
export class UserDetailsComponent implements OnInit {
    public totalScore: any;

    public imgurl = IMG_URL;
    public dataArr: any = {
        check_state: '',
    };
    public checkState: string;
    public causeAnalysis: String = '';
    public isExamine: string;
    public optionId: Array<any> = [];
    public policyAnnex: Array<any> = [];
    public policyName: Array<any> = [];

    constructor(public alert: AlertService,
                public loading: LoadingService,
                public service: UserService,
                public router: ActivatedRoute) {
        this.loading.show();
        this.router.params.subscribe(
            res => {
                this.optionId = res['id'];
                const params: any = {
                    uid: res['id'],
                };
                this.service.seePolicy(params).subscribe(data => {
                        this.isExamine = data['state'];

                        this.loading.hide();
                        if (data['code'] === 0) {
                        this.dataArr = data['data']['user'];
                            this.totalScore = data['data']['totalScore']['totalScore'];

                        // this.policyAnnex = JSON.parse(data['data']['policy_annex']);
                        // this.policyName = JSON.parse(data['data']['policy_annex_name']);

                        }else if (data['code'] === 500 ) {
                          this.alert.show('该用户暂时没有填写个人信息');
                          setTimeout( () => {
                            window.history.go(-1);
                          }, 1000)
                        }
                    },
                    error => {
                        this.loading.hide();
                        this.alert.show('系统繁忙，请联系管理员');
                    });
            }
        )
    }

    ngOnInit() {
    }

    back() {
        window.history.go(-1);
    }

}
