import { Userlogin } from '../entityConstructors/userlogin';
import { Component } from '@angular/core';
import { UrlservicesService } from '../urlservices.service';
import { Loginresponse } from '../LoginResponse';
import { Router } from '@angular/router';
// import { LoginResponse } from './../LoginResponse';


@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent {

  userlogin : Userlogin = new Userlogin("","");
  message = "Either Username or Password is wrong";
  auth = false;

  constructor(private router: Router, private service: UrlservicesService){};

  ngOnInit() : void{
    sessionStorage.removeItem('Key')
  }

  public loginnow()
  {
    let response = this.service.dologin(this.userlogin);
    response.subscribe((resp) => {
      if(resp == "Login success")
      {
        sessionStorage.setItem('Key', this.userlogin.name);
        this.router.navigate(['aba/homepage']);
        this.auth = false;
      }
      else{
        this.auth = true;
      }
    });

  }

}
