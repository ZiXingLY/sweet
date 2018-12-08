import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CropperComponent } from './cropper.component';
import {ModalModule} from 'ngx-bootstrap';

@NgModule({
  imports: [CommonModule, ModalModule],
  declarations: [ CropperComponent ],
  exports: [
    CropperComponent,
  ],
  providers: [],
})
export class CropperModule { }
