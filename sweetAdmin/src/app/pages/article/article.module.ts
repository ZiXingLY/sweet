import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ArticleListComponent} from './article-list/article-list.component';
import {AmendingArticleComponent} from './amending-article/amending-article.component';
import {ViewArticleComponent} from './view-article/view-article.component';
// import {PolicyManagementRoutingModule} from './policy-management-routing.module';
import {ModalModule} from 'ngx-bootstrap/modal';
import {TinymceEditorModule} from '../../shared/tinymce-editor/tinymce-editor.module';
import {FormsModule} from '@angular/forms';
import {PaginationModule} from 'ngx-bootstrap';
// import {PolicyManagementService} from './policy-management.service';
import {FileInputModule} from '../../shared/file-input/file-input.module';
import {ArticleService} from './article.service'
import {ArticleRoutingModule} from './article-routing.module'

@NgModule({
    imports: [
        CommonModule,
        ArticleRoutingModule,
        FormsModule,
        FileInputModule,
        CommonModule,
        PaginationModule,
        ModalModule.forRoot(),
        TinymceEditorModule,
    ],
    providers: [ArticleService],
    declarations: [AmendingArticleComponent, ArticleListComponent, ViewArticleComponent]
})
export class ArticleModule {
}
