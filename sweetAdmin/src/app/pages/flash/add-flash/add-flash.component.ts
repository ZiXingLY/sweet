import { Component, OnInit, EventEmitter, ViewChild } from '@angular/core';
import {AlertService} from '../../../shared/alert.service';
import {ActivatedRoute} from '@angular/router';
import {LoadingService} from '../../../shared/loading.service';
import { CropperComponent } from '../../../shared/cropper/cropper.component';
import {FlashService} from "../flash.service"

@Component({
  selector: 'app-add-flash',
  templateUrl: './add-flash.component.html',
  styleUrls: ['./add-flash.component.scss']
})
export class AddFlashComponent implements OnInit {

    @ViewChild(CropperComponent) private cropperComponent: CropperComponent;
    // 向富文本发送事件
    public receiveContent: EventEmitter<any> = new EventEmitter();
    public labelId: any = 1;
    public proofBo: boolean;
    public bgImg: any;
    public uploadpictures: String = '上传图片并裁剪';
    public detailsPictures: Boolean = false;

    public labelList: Array<any> = [];
    public labelList1: Array<any> = [];
    public labelList2: Array<any> = [];
    public baseData: Array<any> = [];
    public policyAnnex: Array<any> = [];
    public policyName: Array<any> = [];
    public optionId: any;
    public dataArr: any = {
        policy_name: '',
        policy_content: '',

    };
    constructor(
        public alert: AlertService,
        public loading: LoadingService,
        public service: FlashService,
        public router: ActivatedRoute,
    ) {}

    ngOnInit() {
        this.getlabel();
        // this.getBase();
        //  this.router.params.subscribe(
        //     res => {
        //         this.optionId = res['id'];
        //         if (this.optionId !== undefined) {
        //             const params: any  = {
        //                 id: this.optionId,
        //             }
        //             this.service.seePolicy(params).subscribe(data => {
        //                     console.log(data);
        //                     this.loading.hide();
        //                     if (data['code']  === 0) {
        //                         this.dataArr = data['data'];
        //                         console.log(Number(this.dataArr.policy_type2));
        //                         if (Number(this.dataArr.policy_type2) !== 0) {
        //                             this.labelId = this.dataArr.policy_type2;
        //                             this.getlabel(2);
        //                         }
        //                         this.receiveContent.emit(this.dataArr.policy_content);
        //                         if (Number(this.dataArr.policy_type2) !== 0) {
        //                             this.labelId = this.dataArr.policy_type3;
        //                             this.getlabel(3);
        //                         }
        //                         this.policyAnnex = JSON.parse(data['data']['policy_annex']);
        //                         this.policyName = JSON.parse(data['data']['policy_annex_name']);
        //                     }
        //                 },
        //                 error => {
        //                     this.loading.hide()
        //                     this.alert.show('系统繁忙，请联系管理员');
        //                 });
        //         }
        //     }
        // )
        // if (this.optionId === undefined) {
        // }
    }
    // 商品详情
    updateContent(obj) {
        this.dataArr.policy_content = obj;
    }
    // getBase() {
    //     const params: any = {}
    //     this.service.baseId(params).subscribe(
    //         res => {
    //             this.loading.hide();
    //             if (res['code'] === 0) {
    //                 this.baseData = res['data'];
    //             } else {
    //                 this.alert.show(res['message']);
    //             }
    //         },
    //         error => {
    //             this.loading.hide();
    //             this.alert.show('系统繁忙,请联系管理员');
    //         }
    //     );
    // }
    updateImgFile(event) {
        const file = event.target.files[0] || event.dataTransfer.files[0];
        if (file.type === 'application/pdf' || file.type === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document' || file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet') {
            const reader = new FileReader();
            const files = file;
            const r = new FileReader();  // 本地预览
            r.onload = () => {
                this.policyAnnex.push(r.result);
                this.policyName.push(file.name);
            };
            r.readAsDataURL(files);    // Base64
        }else {
            this.alert.show('文件格式错误，要求是PDF;DOCX,XLSX格式。');
        }
    }
    deleteimg(i) {
        this.policyName.splice(i,1);
        this.policyAnnex.splice(i,1);
    }
    getlabel() {
        const params: any = {
            // id: this.labelId,
            // token: localStorage.token
        };

        this.service.labelList(params).subscribe(
            res => {

                this.loading.hide();
                this.labelList = res;
                // console.log(this.labelList)

            },
            error => {
                this.loading.hide();
                this.alert.show('系统繁忙，请联系管理员');
            }
        );
    }
    goBack() {
        window.history.go(-1);
    }
    saveData() {
        if (this.dataArr.policy_name === '') {
            this.alert.show('请输入文章名称');
            return;
        }
        if (this.dataArr.policy_content === '') {
            this.alert.show('请输入文章内容');
            return
        }
        this.loading.show();
        this.dataArr.policy_annex_name = JSON.stringify(this.policyName);
        this.dataArr.policy_annex = JSON.stringify(this.policyAnnex);
        const params: any = {
            title: this.dataArr.policy_name,
            content: this.dataArr.policy_content	,
        };

        this.service.popstflash(params).subscribe(
            res => {
                console.log(res);

                this.loading.hide();
                if (res['code'] === 0) {
                    this.alert.show('新增成功');
                    setTimeout(() => {
                        window.history.go(-1);
                    }, 2000);
                } else {
                    this.alert.show(res['message']);
                }
            },
            error => {
                this.loading.hide();
                this.alert.show('服务器错误,请联系管理员');
            }
        )
    }
    updateCroppersImg() {
        this.cropperComponent.show();
    }
    //  获取图片
    commodityPictures (event) {
        this.dataArr.icon = event;  // 这是选图后点击确定显示在浏览器页面上
        this.proofBo = false;
    }
    //  删除证件
    deleteCertificate (i) {
        this.dataArr.icon = '';
    }
    // updateContentimg(obj) {
    //     this.dataArr.content = obj;
    // }
    updatebgImg(event) {
        const file = event.target.files[0] || event.dataTransfer.files[0];
        if (file.size > 10485760) {
            this.alert.show('图片不超过10M');
        }else if (file.type === 'image/png' || file.type === 'image/jpg' || file.type === 'image/jpeg') {
            const reader = new FileReader();
            const files = file;
            const r = new FileReader();  // 本地预览
            r.onload = () => {
                this.bgImg = String(r.result);
                this.uploadpictures = '重新上传';
                this.detailsPictures = true;
            };
            r.readAsDataURL(files);    // Base64
        }else {
            this.alert.show('图片格式错误，要求是jpg、jpeg、png格式。');
        }
    }
}
