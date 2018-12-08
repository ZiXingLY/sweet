import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

//  子component
import { MessageListComponent } from './message-list/message-list.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'index',
        component: MessageListComponent,
        data: {
          title: '系统消息'
        }
      }
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MessageRoutingModule {}
