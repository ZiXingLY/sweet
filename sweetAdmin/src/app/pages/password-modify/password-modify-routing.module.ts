import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// 子component
import { ModifyPageComponent } from './modify-page/modify-page.component';
const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'index',
        component: ModifyPageComponent,
        data: {
          title: '密码修改'
        }
      }
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule {}
