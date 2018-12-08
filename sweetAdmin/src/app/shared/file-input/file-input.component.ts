import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import { AlertService } from '../alert.service';

@Component({
  selector: 'app-file-input',
  templateUrl: './file-input.component.html'
})
export class FileInputComponent implements OnInit {
  @Input() public selectFile: EventEmitter<any> = new EventEmitter();
  @Output() public updateFile: EventEmitter<any> = new EventEmitter();
  constructor(public ele: ElementRef,
              public alert: AlertService) {
  }
  ngOnInit() {
    this.selectFile.subscribe(() => {
      const fileInput = this.ele.nativeElement.querySelector('#fileUploadInput');
      fileInput.click();
      fileInput.addEventListener('change', () => {
        const file = fileInput.files[0];
        console.log(file);
        if (file.size > 15728640) {
          this.alert.show('视频大小不能超过15M');
        } else if (file) {
          const reader = new FileReader();
          reader.onload = () => {
            this.updateFile.emit(reader.result);
          };
          reader.readAsDataURL(file);
        }
      });
    });
  }
}

