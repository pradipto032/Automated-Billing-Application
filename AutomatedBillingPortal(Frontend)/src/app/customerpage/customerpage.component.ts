import { Component, OnInit } from '@angular/core';
import { UrlservicesService } from '../urlservices.service';

@Component({
  selector: 'app-customerpage',
  templateUrl: './customerpage.component.html',
  styleUrls: ['./customerpage.component.css']
})
export class CustomerpageComponent implements OnInit {

  public cust : any;

  constructor(private service : UrlservicesService) { }

  ngOnInit(): void {
    let response = this.service.getCustomers();
    response.subscribe(resp => this.cust = resp)
  }

}
