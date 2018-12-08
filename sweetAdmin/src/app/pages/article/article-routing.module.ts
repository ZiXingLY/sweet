import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// 子component
import {AmendingArticleComponent} from './amending-article/amending-article.component';
import { ArticleListComponent } from './article-list/article-list.component';
import {ViewArticleComponent} from './view-article/view-article.component';
const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'index',
        component: ArticleListComponent,
        data: {
          title: '文章列表'
        }
      },
        {
            path: 'index/view/:id/:type',
            component: ViewArticleComponent,
            data: {
                title: '文章详情'
            }
        },
      {
        path: 'index/add',
        component: AmendingArticleComponent,
        data: {
          title: '新增文章'
        }
      },
        {
            path: 'index/edit/:id',
            component: AmendingArticleComponent,
            data: {
                title: '编辑文章'
            }
        },
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ArticleRoutingModule {}
