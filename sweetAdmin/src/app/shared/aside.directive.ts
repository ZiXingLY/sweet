import { Directive, HostListener } from '@angular/core';

/**
* Allows the aside to be toggled via click.
*/
// 用于点击头像旁的侧边按钮显示的侧边栏
@Directive({
  selector: '[appAsideMenuToggler]',
})
export class AsideToggleDirective {
  constructor() { }

  @HostListener('click', ['$event'])
  toggleOpen($event: any) {
    $event.preventDefault();
    document.querySelector('body').classList.toggle('aside-menu-hidden');
  }
}
