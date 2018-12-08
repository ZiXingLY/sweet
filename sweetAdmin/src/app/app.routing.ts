import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

// Layouts
import {FullLayoutComponent} from './layouts/full-layout.component';

// preloading
import {SelectivePreloadingStrategy} from './app.preloading-strategy';
import {LoginComponent} from './pages/login/login.component';




const permissions: any = localStorage.permissions ? JSON.parse(localStorage.permissions) : [{controller: ''}];
const redirectUrl: any = permissions[0].controller ? permissions[0].controller + '/index' : 'login';
const route_login: any = {
    path: '',
    redirectTo: redirectUrl,
    pathMatch: 'full'
};

export const routes: Routes = [
    // {
    //     path: '',
    //     redirectTo: 'user/index',
    //     pathMatch: 'full'
    // },
    {
        path: 'login',
        component: LoginComponent,
    },
    route_login,
    {
        path: '',
        component: FullLayoutComponent,
        data: {
            title: '后台管理中心'
        },
        children: [
            // {
            //     path: 'login',
            //     loadChildren: './pages/login/login.module#LoginModule',
            //     data: {
            //         preload: true
            //     }
            // },
            // {
            //     path: 'banner',
            //     loadChildren: './pages/banner/banner.module#BannerModule',
            //     data: {
            //         preload: true
            //     }
            // },
            // 用户管理
            {
                path: 'user',
                loadChildren: './pages/user/user.module#UserModule',
                data: {
                    preload: true
                }
            },
            {
                path: 'article',
                loadChildren: './pages/article/article.module#ArticleModule',
                data: {
                    preload: true
                }
            },
            {
                path: 'class',
                loadChildren: './pages/classianagement/classianagement.module#ClassianagementModule',
                data: {
                    preload: true
                }
            },
            {
                path: 'flash',
                loadChildren: './pages/flash/flash.module#FlashModule',
                data: {
                    preload: true
                }
            },
            // 系统管理
            {
                path: 'operate-user',
                loadChildren: './pages/operate-user/operate-user.module#OperateUserModule',
                data: {
                    preload: true
                }
            },


            {
                path: 'role',
                loadChildren: './pages/role/role.module#RoleModule',
                data: {
                    preload: true
                }
            },
            // {
            //     path: 'message',
            //     loadChildren: './pages/message/message.module#MessageModule',
            //     data: {
            //         preload: true
            //     }
            // },


            {
                path: 'passwordmodify',
                loadChildren: './pages/password-modify/password-modify.module#PasswordModifyModule',
                data: {
                    preload: true
                }
            },
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {preloadingStrategy: SelectivePreloadingStrategy})],
    exports: [RouterModule],
    providers: [SelectivePreloadingStrategy]
})
export class AppRoutingModule {
}
