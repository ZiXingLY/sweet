import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FileInputComponent } from './file-input.component';

@NgModule({
  imports: [CommonModule],
  declarations: [ FileInputComponent ],
  exports: [
    FileInputComponent,
  ],
  providers: [],
})
export class FileInputModule { }
