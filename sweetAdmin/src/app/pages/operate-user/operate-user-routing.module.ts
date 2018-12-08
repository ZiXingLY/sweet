import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

// 子component
import {OperateUserListComponent} from './operate-user-list/operate-user-list.component';
import {OperateModifyComponent} from './operate-modify/operate-modify.component';

const routes: Routes = [
    {
        path: '',
        data: {},
        children: [
            {
                path: 'index',
                component: OperateUserListComponent,
                data: {
                    title: '人员管理'
                }
            },
            {
                path: 'index/add',
                component: OperateModifyComponent,
                data: {
                    title: '添加人员'
                }
            },
            {
                path: 'index/modify/:id/:account/:name/:role',
                component: OperateModifyComponent,
                data: {
                    title: '编辑人员'
                }
            },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class OperateUserRoutingModule {
}
