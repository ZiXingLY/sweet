import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ClassianagementComponent} from './classianagement/classianagement.component'
// 子component

const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: 'index',
                component: ClassianagementComponent,
                data: {
                    title: '分类管理'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ClassianagementRoutingModule {
}
