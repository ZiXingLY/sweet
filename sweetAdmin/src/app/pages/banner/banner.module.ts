import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';

// 分页组件
import {PaginationModule} from 'ngx-bootstrap';

// 组件
import { BannerComponent } from './banner-list/banner.component';
import { BannerRoutingModule } from './banner-routing.module';
import { TinymceEditorModule } from '../../shared/tinymce-editor/tinymce-editor.module';
import {BannerService} from './banner.service';
import {CropperModule} from '../../shared/cropper/cropper.module';
import {CommonModule} from '@angular/common';

@NgModule({
  imports: [
    BannerRoutingModule,
    FormsModule,
    CommonModule,
    PaginationModule,
    ModalModule.forRoot(),
    TinymceEditorModule,
    CropperModule
  ],
  providers: [BannerService],
  declarations: [BannerComponent]
})
export class BannerModule {
}
