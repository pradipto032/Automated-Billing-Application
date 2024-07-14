import { Component, OnInit } from '@angular/core';
import { ProductbillingpageComponent } from '../productbillingpage/productbillingpage.component';


@Component({
  selector: 'app-sendmailform',
  templateUrl: './sendmailform.component.html',
  styleUrls: ['./sendmailform.component.css']
})
export class SendmailformComponent implements OnInit {

  constructor(private pro : ProductbillingpageComponent) { }

  ngOnInit(): void {
  }
  public p : any;

  // public asd(){
  //   p = this.pro.sendmail(s:any);
  // }



}
