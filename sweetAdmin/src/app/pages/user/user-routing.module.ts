import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

// 子component
import {UserListComponent} from './user-list/user-list.component'
import {UserDetailsComponent} from './user-details/user-details.component'
import {IntegraldetailComponent} from './integraldetail/integraldetail.component'

const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: 'index',
                component: UserListComponent,
                data: {
                    title: '用户管理'
                }
            },
            {
                path: 'index/details/:id',
                component: UserDetailsComponent,
                data: {
                    title: '用户详情'
                }
            },
            {
                path: 'index/integral/:id',
                component: IntegraldetailComponent,
                data: {
                    title: '积分明细'
                }
            }

        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class UserRoutingModule {
}
