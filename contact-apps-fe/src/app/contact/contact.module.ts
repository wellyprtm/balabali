import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AccordionModule } from 'primeng/accordion';
import { MessageService } from 'primeng/api';
import { TableModule } from 'primeng/table';

import { ContactComponent } from './contact.component';
import { ContactRoutingModule } from './contact.routing';

@NgModule({
  imports: [
    FormsModule,
    ReactiveFormsModule,
    AccordionModule,
    ContactRoutingModule,
    TableModule
  ],
  exports: [ContactComponent],
  declarations: [ContactComponent],
  providers: [MessageService]
})
export class ContactModule {}
