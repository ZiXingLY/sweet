import { Component, OnInit } from '@angular/core';
import {Swiper} from 'swiper';

@Component({
  selector: 'app-swt-swiper',
  templateUrl: './swt-swiper.component.html',
  styleUrls: ['./swt-swiper.component.css']
})
export class SwtSwiperComponent implements OnInit {

  testSwiper: Swiper;
  slides = [
    'https://via.placeholder.com/300x200/FF5733/ffffff',
    'https://via.placeholder.com/300x200/C70039/ffffff',
    'https://via.placeholder.com/300x200/900C3F/ffffff'
  ];

  constructor() { }

  ngOnInit() {

    this.testSwiper = new Swiper('.swiper-container', {
      direction: 'horizontal',
      loop: true,
      // 如果需要分页器
      pagination: {
        el: '.swiper-pagination',
      },
      // 如果需要前进后退按钮
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
      // 如果需要滚动条
      scrollbar: {
        el: '.swiper-scrollbar',
      },
    });
  }

}
