import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';

// 组件
import { RoleListComponent } from './role-list/role-list.component';
import { RoleService } from './role.service';
// routing
import { RoleRoutingModule } from './role-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ModalModule.forRoot(),
    RoleRoutingModule
  ],
  providers: [RoleService],
  declarations: [RoleListComponent]
})
export class RoleModule { }
