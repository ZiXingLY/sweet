import {Component, OnInit} from '@angular/core';
import {RoleService} from '../role.service';
import {LoadingService} from '../../../shared/loading.service';
import {AlertService} from '../../../shared/alert.service';

@Component({
    selector: 'app-role-list',
    templateUrl: './role-list.component.html',
    styleUrls: ['./role-list.component.scss']
})
export class RoleListComponent implements OnInit {
    public roleFunction: Array<any> = [];
    public roleName: string;
    // 保存全部权限数据
    public determinationData: Array<any> = [];
    //  保存选中的权限id
    public permissionsId: Array<any> = [];
    public checkPermissionsid: Array<any> = [];
    public checkPermissionsidString: string;

    // 删除ID
    public delectindexId: number;
    public noDatastring: Boolean = false;
    // 模态窗口
    public delectModal: Boolean = false;
    public dangerModal: Boolean = false;
    public JurisdictionModal: Boolean = false;
    public completePrompt: string;
    public modelBody: string;
    public issuccess: Boolean = false;
    public page: any = 1;

    public role_id: any;

    constructor(public loading: LoadingService,
                private serves: RoleService,
                public alert: AlertService) {
    }

    ngOnInit() {
        this.loading.show();
        const params: any = {
            pageNum: this.page
        };
        this.serves.getRoles(params).subscribe(
            res => {
                if (res['data']['length'] === 0) {
                    this.noDatastring = true;
                } else {
                    this.noDatastring = false;
                    this.roleFunction = res['data']['list'];

                }
                setTimeout(() => {
                    this.loading.hide();
                }, 300);
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
        this.serves.authorityRoles(params).subscribe(
            res => {
                this.determinationData = res['data'];
                for (let i = 0; i < this.determinationData.length; i++) {
                    this.determinationData[i]['isChecked'] = false;
                }
            }
        );
    }

    delectIndex(id: number) {
        this.delectindexId = id;
        this.dangerModal = true;
        this.completePrompt = '你确定要删除?';
    }

    addmodify() {
        this.loading.show();
        if (this.roleName === undefined || this.roleName === '') {
            this.delectModal = true;
            setTimeout(() => {
                this.loading.hide();
            }, 300);
            this.issuccess = false;
            this.modelBody = '名称不能为空';
        } else {
            const params: any = {
                title: this.roleName,
            };
            this.serves.saveRoles(params).subscribe(
                res => {
                    setTimeout(() => {
                        this.loading.hide();
                    }, 300);
                    if (res['code'] === 0) {
                        this.delectModal = true;
                        this.issuccess = true;
                        this.modelBody = '管理角色添加成功';
                    } else {
                        this.delectModal = true;
                        this.issuccess = false;
                        this.modelBody = res['msg'];
                    }
                },
                error => {
                    this.loading.hide();
                    this.alert.show('网络错误');
                }
            );
        }
    }

    // 删除确认
    confirmshutdown() {
        this.loading.show();
        this.delectModal = false;
        this.roleName = '';
        if (this.issuccess === true) {
            const params: any = {
                pageNum: this.page
            };
            this.serves.getRoles(params).subscribe(
                res => {
                    if (res['data']['length'] === 0) {
                        this.noDatastring = true;
                    } else {
                        this.noDatastring = false;
                        this.roleFunction = res['data']['list'];
                    }
                    setTimeout(() => {
                        this.loading.hide();
                    }, 300);
                },
                error => {
                    this.loading.hide();
                    this.alert.show('网络错误');
                }
            );
        }
        setTimeout(() => {
            this.loading.hide();
        }, 300);
    }

    hideModal() {
        this.dangerModal = false;
        setTimeout(() => {
            this.loading.hide();
        }, 300);
    }

    // 删除模态
    confirmDelete() {
        this.loading.show();
        const params: any = {
            id: this.delectindexId,
        };
        this.serves.deleteRoles(params).subscribe(
            res => {
                this.dangerModal = false;
                this.loading.hide();
                if (res['code'] === 0) {
                    this.modelBody = '删除成功';
                    this.dangerModal = false;
                    this.delectModal = true;
                    this.issuccess = true;
                } else {
                    this.modelBody = res['msg'];
                    this.delectModal = true;
                    this.issuccess = false;
                }
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
    }

//   配置权限
    operation(id: number) {
        this.loading.show();
        this.delectindexId = id;
        const params: any = {
            role_id: id
        };
        this.serves.oneAuthorityRoles(params).subscribe(
            res => {
                this.loading.hide();
                if (res['data']['length'] !== 0) {
                    this.permissionsId = res['data'];
                    for (let i = 0; i < this.determinationData.length; i++) {
                        for (let j = 0; j < this.permissionsId.length; j++) {
                            if (this.determinationData[i]['id'] == this.permissionsId[j]['id']) {
                                this.determinationData[i]['isChecked'] = true;
                                this.checkPermissionsid.push(this.determinationData[i]['id']);
                            }
                        }
                    }
                }
                this.JurisdictionModal = true;
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
    }

    selectCheckbox(check: number, item: any) {
        if (check) {
            this.checkPermissionsid.push(item.id);
        } else {
            for (let i = 0; i < this.checkPermissionsid.length; i++) {
                if (this.checkPermissionsid[i] === item['id']) {
                    this.checkPermissionsid.splice(i, 1);
                }
            }
        }
    }

    Jurisdiction() {
        this.JurisdictionModal = false;
        this.checkPermissionsid = [];
        for (let i = 0; i < this.determinationData.length; i++) {
            this.determinationData[i]['isChecked'] = false;
        }
    }

    Authoritydetermination() {
        this.loading.show();
        this.JurisdictionModal = false;
        this.checkPermissionsidString = JSON.stringify(this.checkPermissionsid);
        // this.checkPermissionsidString = this.checkPermissionsidString.substring(1, this.checkPermissionsidString.length - 1);
        const params: any = {
            role_id: this.delectindexId,
            permissionsList: this.checkPermissionsid,
        };
        this.serves.operationRoles(params).subscribe(
            res => {
                this.loading.hide();
                if (res['code'] === 0) {
                    this.delectModal = true;
                    this.issuccess = true;
                    this.modelBody = '权限修改成功';
                } else {
                    this.delectModal = true;
                    this.issuccess = false;
                    this.modelBody = res['message'];
                }
            },
            error => {
                this.loading.hide();
                this.alert.show('网络错误');
            }
        );
        this.checkPermissionsid = [];
        for (let i = 0; i < this.determinationData.length; i++) {
            this.determinationData[i]['isChecked'] = false;
        }
    }
}
