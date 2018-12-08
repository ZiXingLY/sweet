import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {BannerService} from '../banner.service';
import {LoadingService} from '../../../shared/loading.service';
import {AlertService} from '../../../shared/alert.service';
import {CropperComponent} from '../../../shared/cropper/cropper.component';


@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.scss']
})

export class BannerComponent implements OnInit {
  @ViewChild(CropperComponent) private cropperComponent: CropperComponent;
  public bannerSort: number; // 图片顺序
  public bannerUrl: any; // 图片路径
  public bannerShowAddress: any = 1; // 展示位置


  public smallModal: any;
  public imgurl: string;
  public imgurls: string;
  public bannerList: Array<any> = [];
  // 判断是否有数据
  public isnoData: Boolean = false;
  // 保存图片请求头
  public imgUrl: string;
  // 提示文字
  public modalText: string;
  // 提示模态框
  public dangerModal: Boolean = false;
  public successText: string;
  public issuccess: Boolean = false;
  // 区分上架或者是删除
  public shelvesorDeletes: Boolean = false;
  // 分页组件相关配置
  public page: any = 1;
  public totalItems: any; // 列表数据总数
  public currentPage: any = 1; // 默认的开始页数
  public maxSize: Number = 3; // 显示的最大按钮数量
  // 保存id和上架信息
  public bannerItem: any = {
    id: '',
    state: '',
  };
  public showAddress: any = []; // 图片展示的位置

  public cropperImg: any = '';    // 图片展示位置
  public uploadpictures: any = '上传图片并裁剪'; // 提示
  public detailsPicture: Boolean = false;


  constructor(public ele: ElementRef,
              public service: BannerService,
              public loading: LoadingService,
              public alert: AlertService) {
  }

  ngOnInit() {
    this.loading.show();
    this.imgUrl = IMG_URL;
    this.getaData();
  }

  // 提交轮播图数据
  submits() {
    if (!this.bannerSort) {
      this.alert.show('请填写轮播排序');
      return;
    }
    if (!this.cropperImg) {
      this.alert.show('请选择轮播图图片');
      return;
    }
    if (!this.bannerUrl) {
      this.alert.show('请填写图片路径');
      return;
    }
    const params: any = {
      picture: this.cropperImg, // 图片路径
      url: this.bannerUrl, // 图片链接
      address: this.bannerShowAddress, // 1政策 2基地
      sort: this.bannerSort  // 排序
    };
    this.loading.show();
    this.service.addNewBanner(params).subscribe(
      res => {
        if (res['code'] == 0) {
          this.loading.hide();
          this.alert.show('轮播图添加成功');
          this.getaData();
        } else {
          this.loading.hide();
          this.alert.show(res['message']);

        }
      },
      error => {
        this.alert.show(error['message']);
        this.loading.hide();
      }
    )
  }

  // 获取轮播图列表
  getaData() {
    const params: any = {
      pageNum: this.page,
    };
    this.service.bannerList(params).subscribe(
      res => {
        if (res['data']['length'] === 0) {
          this.isnoData = true;
        } else {
          this.bannerList = res['data']['list'];
          this.totalItems = res['data']['total'];
          this.bannerList.map((v) => {
            if (v['picture'].indexOf('[') != -1) {
              v['picture'] = JSON.parse(v['picture'])[0];
            }
          })
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

  updateCropperImg(data) {
    this.imgurl = data;
  }

  updateCropperImgs(data) {
    this.imgurls = data;
  }

  openLoading() {
    this.loading.show();
  }

  pageChanged(event: any): void {
    // 切换分页进行网络请求
    this.loading.show();
    this.page = event.page;
    this.getaData();
  }

  // 点击上架
  clickShelves(id, state) {
    this.bannerItem.id = id;
    this.bannerItem.state = state;
    this.shelvesorDeletes = true;
    if (this.bannerItem.state == 2) {
      this.modalText = '您确定要上架轮播图?';
    } else {
      this.modalText = '您确定要下架轮播图?';
    }
  }


  // 删除
  deleteitem(id, is_shelves) {
    this.bannerItem.is_shelves = is_shelves;
    this.shelvesorDeletes = false;
    this.modalText = '您确定要删除？';
    this.bannerItem.id = id;
  }

  // 上下架、删除
  definiteRequest() {
    this.loading.show();
    if (this.shelvesorDeletes === true) {
      if (this.bannerItem.is_shelves == 0) {
        this.bannerItem.is_shelves = 1;
      } else {
        this.bannerItem.is_shelves = 0;
      }
      const params: any = {
        id: this.bannerItem.id,
      };
      if (this.bannerItem.state == 2) { // 上架
        this.service.bannerUpShelves(params).subscribe(
          res => {
            this.loading.hide();
            if (res['code'] === 0) {
              this.successText = '上架成功';
              this.issuccess = true;
              this.dangerModal = true;
            } else {
              this.successText = '操作失败';
              this.issuccess = false;
              this.dangerModal = true;
            }
          },
          error => {
            this.loading.hide();
            this.alert.show('网络错误');
          }
        );
      } else {  // 下架
        this.service.bannerDownShelves(params).subscribe(
          res => {
            this.loading.hide();
            if (res['code'] === 0) {
              this.successText = '下架成功';
              this.issuccess = true;
              this.dangerModal = true;
            } else {
              this.successText = '操作失败';
              this.issuccess = false;
              this.dangerModal = true;
            }
          },
          error => {
            this.loading.hide();
            this.alert.show('网络错误');
          }
        );
      }

    } else {
      if (this.bannerItem.is_shelves === 1) {
        this.successText = '该轮播图已上架，不能删除';
        this.issuccess = false;
        this.dangerModal = true;
      } else {
        const params: any = {
          id: this.bannerItem.id,
        };
        this.service.bannerDeletes(params).subscribe(
          res => {
            this.loading.hide();
            if (res['code'] === 0) {
              this.successText = '删除成功';
              this.issuccess = true;
              this.dangerModal = true;
            } else {
              this.successText = '删除失败';
              this.issuccess = false;
              this.dangerModal = true;
            }
          },
          error => {
            this.loading.hide();
            this.alert.show('网络错误');
          }
        );
      }
    }
  }

  hideModal() {
    this.dangerModal = false;
    this.loading.show();
    this.getaData();
  }

  // 图片上传
  updateCroppersImg() {
    this.cropperComponent.show();
  }

//  获取图片
  commodityPictures(event) {
    const file = event.target.files[0] || event.dataTransfer.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.cropperImg = reader.result;
    };
    reader.readAsDataURL(file);
    this.uploadpictures = '重新上传';
    this.detailsPicture = true;
  }

//  删除图片
  deleteimg() {
    this.cropperImg = '';
    this.detailsPicture = false;
    this.uploadpictures = '上传图片并裁剪';
  }
}
