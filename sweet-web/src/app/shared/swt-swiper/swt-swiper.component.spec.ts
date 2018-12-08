import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SwtSwiperComponent } from './swt-swiper.component';

describe('SwtSwiperComponent', () => {
  let component: SwtSwiperComponent;
  let fixture: ComponentFixture<SwtSwiperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SwtSwiperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SwtSwiperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
