import { Component, OnInit } from '@angular/core';
import { Userregistration } from '../entityConstructors/userregister';
import { UrlservicesService } from '../urlservices.service';

@Component({
  selector: 'app-registerpage',
  templateUrl: './registerpage.component.html',
  styleUrls: ['./registerpage.component.css']
})
export class RegisterpageComponent implements OnInit {

  userregister : Userregistration = new Userregistration("","","");
  message : any;
  show1 = false;

  constructor(private service: UrlservicesService) { }

  ngOnInit(): void {
  }

  public registernow()
  {
    let response = this.service.doRegistration(this.userregister);
    response.subscribe(resp => {
      if(resp == "User registered")
      {
        this.message = "Thank you for registering. Please go to login page and log in."
        this.show1 = true;
      }
      else
      {
        this.message = "Already registered. Please go to login page and log in."
        this.show1 = true;
      }
    });
  }

}
