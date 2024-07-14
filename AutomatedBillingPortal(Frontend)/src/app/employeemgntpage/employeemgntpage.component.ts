import { Component, OnInit } from '@angular/core';
import { UrlservicesService } from '../urlservices.service';
import { deleteEmployee } from '../entityConstructors/deleteEmployee';

@Component({
  selector: 'app-employeemgntpage',
  templateUrl: './employeemgntpage.component.html',
  styleUrls: ['./employeemgntpage.component.css']
})
export class EmployeemgntpageComponent implements OnInit {

  public emp : any;

  constructor(private service : UrlservicesService) { }

  ngOnInit(): void {
    let response = this.service.getAllEmployee();
    response.subscribe(resp => this.emp = resp);
  }

  deleteReq :any;
  public deleteEmpData(gmail: any)
  {
    this.deleteReq = new deleteEmployee(gmail);
    console.log(this.deleteReq);
    let response = this.service.deleteEmployee(this.deleteReq);
    response.subscribe(resp => console.log(resp));

  }


}
