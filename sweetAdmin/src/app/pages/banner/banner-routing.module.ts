import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

// 子component
import {BannerComponent} from './banner-list/banner.component';

const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: 'index',
                component: BannerComponent,
                data: {
                    title: '轮播图管理'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BannerRoutingModule {
}
