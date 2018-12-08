import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SwtSwiperComponent } from './shared/swt-swiper/swt-swiper.component';

@NgModule({
  declarations: [
    AppComponent,
    SwtSwiperComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
