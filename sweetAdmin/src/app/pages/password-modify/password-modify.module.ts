import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';
import { ModifyPageComponent } from './modify-page/modify-page.component';
import { PasswordModifyService } from './password-modify.service';
import { ProductRoutingModule } from './password-modify-routing.module';
@NgModule({
  imports: [
    FormsModule,
    CommonModule,
    ProductRoutingModule,
    ModalModule.forRoot(),
  ],
  providers: [PasswordModifyService],
  declarations: [ModifyPageComponent]
})
export class PasswordModifyModule { }
