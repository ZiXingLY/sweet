import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {ModalModule} from 'ngx-bootstrap/modal';

// 分页组件
import {PaginationModule} from 'ngx-bootstrap';

// 组件
import {TinymceEditorModule} from '../../shared/tinymce-editor/tinymce-editor.module';
import {CropperModule} from '../../shared/cropper/cropper.module';
import {CommonModule} from '@angular/common';
// import {UserListComponent} from './user-list/user-list.component';
import {UserListComponent} from './user-list/user-list.component'
import {UserRoutingModule} from './user-routing.module';
// import {UserService} from './u;
import {UserService} from './user.service'
import {UserDetailsComponent} from './user-details/user-details.component'
import { IntegraldetailComponent } from './integraldetail/integraldetail.component'
@NgModule({
    imports: [
        FormsModule,
        CommonModule,
        PaginationModule,
        ModalModule.forRoot(),
        TinymceEditorModule,
        CropperModule,
        UserRoutingModule
    ],
    providers: [UserService],
    declarations: [UserListComponent, UserDetailsComponent , IntegraldetailComponent]
})
export class UserModule {
}
