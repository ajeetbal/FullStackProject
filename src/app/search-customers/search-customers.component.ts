import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
@Component({
  selector: 'search-customers',
  templateUrl: './search-customers.component.html',
  styleUrls: ['./search-customers.component.css']
})
export class SearchCustomersComponent implements OnInit {
  age: number;
  customers: Customer[];
  constructor(private dataService: CustomerService) { }

  ngOnInit() {
    this.age = 0;
  }
  private searchCustomers() {
    this.dataService.getCustomersByAge(this.age)
      .subscribe(customers => this.customers = customers);
  }
 
  onSubmit() {
    this.searchCustomers();
  }
}
