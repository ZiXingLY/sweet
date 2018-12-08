import { Component, OnInit } from '@angular/core';
import {UserService} from '../user.service'
import {AlertService} from '../../../shared/alert.service'
import {LoadingService} from '../../../shared/loading.service'
import {ActivatedRoute} from '@angular/router'
@Component({
  selector: 'app-integraldetail',
  templateUrl: './integraldetail.component.html',
  styleUrls: ['./integraldetail.component.scss']
})
export class IntegraldetailComponent implements OnInit {
    // 分页
    public page: any = 1;
    public totalItems: any;   // 列表数据总数
    public currentPage: any = 1;   // 默认的开始页数
    public maxSize: Number = 3;    // 显示的最大按钮数量


    public imgurl = IMG_URL;
    public dataArr: Array<any> = [];
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
    }

    ngOnInit() {
        this.getdata();
    }

    back() {
        window.history.go(-1);
    }

    getdata() {
        // this.dataArr = [];
        this.loading.show();
        this.router.params.subscribe(
            res => {
                this.optionId = res['id'];
                const params: any = {
                    uid: res['id'],
                    pageNum: this.page
                }
                this.service.listByUid(params).subscribe(data => {
                        this.isExamine = data['state'];
                        this.loading.hide();
                        if (data['code'] === 0) {
                            this.dataArr = data['data']['list'];
                            this.totalItems = data['data']['total'];

                            // this.policyAnnex = JSON.parse(data['data']['policy_annex']);
                            // this.policyName = JSON.parse(data['data']['policy_annex_name']);

                        }
                    },
                    error => {
                        this.loading.hide();
                        this.alert.show('系统繁忙，请联系管理员');
                    });
            }
        )
    }
    // 切换分页进行网络请求
    pageChanged(event: any): void {
        this.page = event.page;
        this.loading.show();
        this.getdata();
    }
}
