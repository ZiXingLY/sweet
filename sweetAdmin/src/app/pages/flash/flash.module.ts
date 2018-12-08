import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';

// 分页组件
import {PaginationModule} from 'ngx-bootstrap';

// 组件
import { TinymceEditorModule } from '../../shared/tinymce-editor/tinymce-editor.module';
import {CropperModule} from '../../shared/cropper/cropper.module';
import {CommonModule} from '@angular/common';
import {FlashRoutingModule} from './flash-routing.module'
import {FlashService} from './flash.service'
import {FlashListComponent} from './flash-list/flash-list.component'
    ;
import { FlashDetalisComponent } from './flash-detalis/flash-detalis.component'
    ;
// import { AddFlashComponent } from './add-flash/add-flash.component'
import {AddFlashComponent} from './add-flash/add-flash.component'
@NgModule({
  imports: [
      FlashRoutingModule,
    FormsModule,
    CommonModule,
    PaginationModule,
    ModalModule.forRoot(),
    TinymceEditorModule,
    CropperModule
  ],
  providers: [FlashService],
  declarations: [ FlashListComponent, FlashDetalisComponent , AddFlashComponent]
})
export class FlashModule {
}
