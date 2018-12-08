import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// 组件
import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';

import { OperateUserListComponent } from './operate-user-list/operate-user-list.component';
import { OperateSericve } from './operate-user.service';
// routing
import { OperateUserRoutingModule } from './operate-user-routing.module';
import { OperateModifyComponent } from './operate-modify/operate-modify.component';
import { PaginationModule } from 'ngx-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PaginationModule,
    ModalModule.forRoot(),
    OperateUserRoutingModule
  ],
  providers: [OperateSericve],
  declarations: [OperateUserListComponent, OperateModifyComponent]
})
export class OperateUserModule { }
