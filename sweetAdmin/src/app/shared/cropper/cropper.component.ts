import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild, Input} from '@angular/core';
import {ModalDirective} from 'ngx-bootstrap';
import { AlertService } from '../alert.service';

@Component({
  selector: 'app-cropper',
  templateUrl: './cropper.component.html',
  styleUrls: ['./cropper.component.scss']
})
export class CropperComponent implements OnInit {
  @Output() updateCropperImg: EventEmitter<any> = new EventEmitter();
  @ViewChild('cropperModal') public cropperModal: ModalDirective;
  @Input() rule: any;
  public ruleStyle: any = {};
  public canvas_ele: any;
  public isShowModal: any = false;
  constructor(public ele: ElementRef, public alert: AlertService) { }
  ngOnInit() {
      this.ruleStyle = {
          'width': 150 * (this.rule ? this.rule : (16 / 9)) + 'px',
          'height': 150 + 'px',
          'line-height': 150 + 'px'
      };
  }
  onModalShow() {
    this.isShowModal = true;
  }
  onModalHide() {
    this.isShowModal = false;
  }
  show() {
    this.cropperModal.show();
  }
  hide() {
   this.cropperModal.hide();
  }
  updateImgFile(event) {
    const file = event.target.files[0] || event.dataTransfer.files[0];
    if (file === undefined) {
      this.alert.show('请选择图片');
    } else if (file.size > 115343360) {
      this.alert.show('图片不大于10MB');
    }else if (file.type === 'image/png' || file.type === 'image/jpg' || file.type === 'image/jpeg') {
      const reader = new FileReader();
      reader.onload = () => {
        // alert(reader.result.substr(0,100));
        this.ele.nativeElement.querySelector('#cropper_imgDiv').innerHTML = '<img style="width: 100%;height: 300px" id="cropper_image" src="' + reader.result + '">';
        // this.ele.nativeElement.querySelector('#cropper_image').src = reader.result;
        this.ele.nativeElement.querySelector('#filebutton').type = 'button';
        this.ele.nativeElement.querySelector('#filebutton').type = 'file';
        this.ele.nativeElement.querySelector('#cropper_image').onload = (eve) => {
          $(eve.target).cropper({
              aspectRatio: this.rule ? this.rule : (16 / 9),
            crop: (e) => {
              this.canvas_ele = $(e.target).cropper('getCroppedCanvas', {
                width: 600 * 2,
                height: 337.5 * 2,
                beforeDrawImage: function(canvas) {
                  const context = canvas.getContext('2d');
                  context.imageSmoothingEnabled = false;
                  context.imageSmoothingQuality = 'high';
                },
              });
              this.ele.nativeElement.querySelector('#lookDiv').style.backgroundImage = 'url(' + this.canvas_ele.toDataURL('image/png') + ')';
            }
          });
        };
      };
      reader.readAsDataURL(file);
    } else {
        this.alert.show('图片格式错误，要求是jpg、jpeg、png格式。');
    }
  }
  // 点击确定传输图片
  setImgAndCloseModal() {
    if (this.canvas_ele) {
      this.updateCropperImg.emit(this.canvas_ele.toDataURL('image/png'));
      this.ele.nativeElement.querySelector('#lookDiv').style.backgroundImage = '';
      this.ele.nativeElement.querySelector('#cropper_imgDiv').innerHTML = '';
      this.canvas_ele = '';
      return true;
    }else {
      return false;
    }
  }
}

