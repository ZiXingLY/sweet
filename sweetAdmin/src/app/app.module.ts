import { NgModule} from '@angular/core';
import { HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { AppComponent } from './app.component';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

// 分页插件
import {PaginationModule} from 'ngx-bootstrap';
// 富文本编辑组件
import {TinymceEditorModule} from './shared/tinymce-editor/tinymce-editor.module';
// 视频上传插件
import {FileInputModule} from './shared/file-input/file-input.module';
// 自定裁剪图片
import {CropperModule} from './shared/cropper/cropper.module';


// core ui 相关指令
import { NAV_DROPDOWN_DIRECTIVES } from './shared/nav-dropdown.directive';

// 自定义指令
import { SIDEBAR_TOGGLE_DIRECTIVES } from './shared/sidebar.directive';
import { AsideToggleDirective } from './shared/aside.directive';

import { BreadcrumbsComponent } from './shared/breadcrumb.component';

// Routing Module
import { AppRoutingModule } from './app.routing';

// Layouts
import { FullLayoutComponent } from './layouts/full-layout.component';


// 自定义网络参数配置
import {requestOptionsProvider} from './default-request-options.service';

// 自定义加载动画插件
import {LoadingService} from './shared/loading.service';
import {ProcessService} from './shared/process.service';
import {AlertService} from './shared/alert.service';
import {LoginComponent} from './pages/login/login.component';

@NgModule({
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpModule,
        CropperModule,
        FormsModule,
        PaginationModule.forRoot(),
        BsDropdownModule.forRoot(),
        TinymceEditorModule,
        FileInputModule
    ],
    declarations: [
        AppComponent,
        FullLayoutComponent,
        NAV_DROPDOWN_DIRECTIVES,
        BreadcrumbsComponent,
        LoginComponent,
        SIDEBAR_TOGGLE_DIRECTIVES,
        AsideToggleDirective,
    ],
    providers: [{
        provide: LocationStrategy,
        useClass: HashLocationStrategy
    }, requestOptionsProvider, LoadingService, ProcessService, AlertService],
    bootstrap: [ AppComponent ]
})
export class AppModule {}
