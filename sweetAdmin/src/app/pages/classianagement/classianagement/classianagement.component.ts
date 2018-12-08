import { Component, OnInit } from '@angular/core';
import { LoadingService } from '../../../shared/loading.service';
import { AlertService } from '../../../shared/alert.service';
import {ClassianagementService} from '../classianagement.service'
@Component({
  selector: 'app-classianagement',
  templateUrl: './classianagement.component.html',
  styleUrls: ['./classianagement.component.scss']
})
export class ClassianagementComponent implements OnInit {
    public searchQuery: any = {
        primary: '',
        twoStage: '',
    };

    public dangerModal: Boolean = false;
    public issuccess: Boolean = false;
    // public isrelation: Boolean;
    public deleteModal: Boolean = false;
    public completePrompt: string;
    public listIndex: number;



    public storeClass: any;

    public type_name: any;


    // 模态框
    public promptModal: Boolean = false;
    // public issuccess: Boolean = false;
    public isDelect: Boolean = false;
    public isEdit: Boolean = false;
    public delectId: string;
    public dataArr: any = [];
    public datalstArr: any = [];
    public neweditData: any = {
        displayposition: '',
        id: '',
        name: '',
        sort: '',
        pid: '',
    };
    public page: Number = 1;
    public noData: Boolean = false;
    public totalItems: any; // 列表数据总数
    public currentPage: any = 1; // 默认的开始页数
    public maxSize: Number = 3; // 显示的最大按钮数量
    //  弹出层
    public getInputdata = {
        pid : '',
        id : '',
        number: '',
        orderArrangement: '',
        data_number: ''
    };

    constructor(
        public service: ClassianagementService,
        public alert: AlertService,
        public loading: LoadingService,
    ) { }

    ngOnInit() {
       this.getlabel();
    }

    // 删除按钮
    changeUnitSelectIndex (index) {
        this.listIndex = index;
        this.deleteModal = true;
    }
    hidedeleteModal() {
        this.deleteModal = false;
    }
    // 删除商品

//   获取分类数据
    getClassify() {
        this.loading.show();
        const params = {
        };
        this.service.getsave(params).subscribe(res => {
            this.loading.hide();
                this.dataArr = res;
                this.totalItems = res['data']['total'];
        })
    }
    getlabel() {
        const params: any = {
            // id: this.labelId,
            // token: localStorage.token
        };

        this.service.labelList(params).subscribe(
            res => {
                this.dataArr = res;


                this.loading.hide();
                // this.labelList = res;

            },
            error => {
                this.loading.hide();
                this.alert.show('系统繁忙，请联系管理员');
            }
        );
    }
    // 添加数据
    getSever() {
        this.loading.show();
        const type = {
          name: this.type_name
        };
        this.service.getsave(type).subscribe(res => {
            if (res['code'] === 0) {
                this.alert.show('添加成功');

                this.getlabel();

            }

                this.loading.hide();
        })
    }
    delete() {
        this.loading.show();
        const erase = {
            // token: localStorage.token,
            cid: this.listIndex
        };

        this.service.deleteClass(erase).subscribe(res => {
            this.loading.hide();
            if (res['code'] == 0) {
                this.alert.show('删除成功');
                this.getlabel();
            }else {
                this.alert.show(res['message']);
            }
            this.deleteModal  = false;
        })
    }

    pageChanged(event) {
        this.currentPage = event.page;
        this.getClassify();
    }

}
