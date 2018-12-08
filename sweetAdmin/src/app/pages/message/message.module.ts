import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MessageListComponent} from './message-list/message-list.component';
import {ModalModule} from 'ngx-bootstrap/modal';
import {FormsModule} from '@angular/forms';
import {PaginationModule} from 'ngx-bootstrap';
import {MessageRoutingModule} from './message-routing.module';
import {MessageService} from './message.service';


@NgModule({
  imports: [
    CommonModule,
      MessageRoutingModule,
      ModalModule.forRoot(),
      PaginationModule,
      FormsModule,
  ],
    providers: [MessageService],
  declarations: [MessageListComponent]
})
export class MessageModule { }
