import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

// 子component
import {RoleListComponent} from './role-list/role-list.component';

const routes: Routes = [
    {
        path: '',
        data: {},
        children: [
            {
                path: 'roleList',
                component: RoleListComponent,
                data: {
                    title: '角色管理'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class RoleRoutingModule {
}
