import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ngx-bootstrap/modal';

// 分页组件
import {PaginationModule} from 'ngx-bootstrap';

// 组件
import { TinymceEditorModule } from '../../shared/tinymce-editor/tinymce-editor.module';
import {CropperModule} from '../../shared/cropper/cropper.module';
import {ClassianagementService} from './classianagement.service'
import {ClassianagementComponent} from './classianagement/classianagement.component'
import {CommonModule} from '@angular/common';
import {ClassianagementRoutingModule} from './classianagement-routing.module'

@NgModule({
  imports: [
      ClassianagementRoutingModule,
    FormsModule,
    CommonModule,
    PaginationModule,
    ModalModule.forRoot(),
    TinymceEditorModule,
    CropperModule
  ],
  providers: [ClassianagementService],
  declarations: [ ClassianagementComponent]
})
export class ClassianagementModule {
}
