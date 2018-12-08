import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {TinymceEditorComponent} from './tinymce-editor.component';
import { ProgressbarModule } from 'ngx-bootstrap/progressbar';

@NgModule({
  imports: [CommonModule, ProgressbarModule.forRoot(),
  ],
  declarations: [
    TinymceEditorComponent
  ],
  // 这里记得添加TinymceEditorComponent组件，不然在其他地方将不能使用TinymceEditorComponent
  exports: [
    TinymceEditorComponent,
  ],
  providers: [],
})
export class TinymceEditorModule {}
