import { Component } from '@angular/core';

import { ContactService } from './contact.service';

@Component({
  templateUrl: './contact.component.html'
})
export class ContactComponent {

  contactList: any;
  filter: any;
  totalRecords: number;
  constructor(
    private contactService: ContactService
  )
  {
    this.filter = {};
    this.totalRecords = 0;
  }

  async loadData(event?: any): Promise<void> {
    console.log(event);
    if (!event) {
     this.filter.page = 0;
     this.filter.pageSize = 10;
    } else {
      this.filter.page = Math.trunc(event['first'] / event['rows']);
      this.filter.pageSize = event['rows'];
    }

    console.log(this.filter)
    this.contactService.search(this.filter)
    .subscribe(r => {
      console.log(r);
      if (r.data) {
        this.contactList = r.data;
        this.totalRecords = r.totalRecords;
      } else {
        this.contactList = [];
        this.totalRecords = 0;
      }
    })

  }

}
