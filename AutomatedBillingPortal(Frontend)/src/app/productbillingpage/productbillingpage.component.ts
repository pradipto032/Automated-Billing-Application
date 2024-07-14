import { Customers } from './../entityConstructors/customers';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UrlservicesService } from '../urlservices.service';
import { Address } from '../entityConstructors/address';
import { Billsending } from '../entityConstructors/billsending';
import { productmodel } from '../entityConstructors/productModel';


@Component({
  selector: 'app-productbillingpage',
  templateUrl: './productbillingpage.component.html',
  styleUrls: ['./productbillingpage.component.css']
})
export class ProductbillingpageComponent implements OnInit {

  public cust : any;
  prod : productmodel = new productmodel("",0,0,"");
  // bill : Billsending = new Billsending(new Customers("","","",new Address("","",""),new Address("","","")),this.prod,0);

  constructor(private service : UrlservicesService, private router : Router) { }

  ngOnInit(): void {
    let response = this.service.getCustomers();
    response.subscribe(resp => this.cust = resp)
  }

  public b : any;
  public productName = "";
  public productPrice = 0;
  public quantity = 0;
  public orderStatus = "";
  public advancePaid = 0;

  public sendmail(p : any)
  {
    this.b = new Billsending(p, this.prod, this.advancePaid)
    console.log(this.b);
    let response = this.service.sendmail1(this.b);
    response.subscribe(resp => console.log(resp));
  }
}
