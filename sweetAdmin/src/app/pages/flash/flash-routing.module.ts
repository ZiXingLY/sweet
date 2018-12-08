import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {FlashListComponent} from './flash-list/flash-list.component'
import {FlashDetalisComponent} from './flash-detalis/flash-detalis.component'
import {AddFlashComponent} from './add-flash/add-flash.component'
// 子component

const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: 'index',
                component: FlashListComponent,
                data: {
                    title: '快讯管理'
                }
            },
            {
                path: 'index/details/:id',
                component: FlashDetalisComponent,
                data: {
                    title: '快讯详情'
                }
            }
            ,
            {
                path: 'index/add',
                component: AddFlashComponent,
                data: {
                    title: '添加快讯'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class FlashRoutingModule {
}
