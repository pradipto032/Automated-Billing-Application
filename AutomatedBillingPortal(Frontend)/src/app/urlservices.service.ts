
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Userregistration } from './entityConstructors/userregister';
import { employee } from './employee';
import { Observable } from 'rxjs';
import { Customers } from './entityConstructors/customers';


@Injectable({
  providedIn: 'root'
})
export class UrlservicesService {

  constructor(private http: HttpClient) { }

  url = "http://localhost:9090";

  public doRegistration(userregistration: any){
    return this.http.post(this.url + "/auth-server/bill/auth/register", userregistration, {responseType: 'text' as 'json'});
  }

  public dologin(userlogin : any){
    return this.http.post(this.url + "/auth-server/bill/auth/login", userlogin, {responseType: 'text' as 'json'})
  }

  public getAllEmployee() : Observable<employee[]>{
    return this.http.get<employee[]>(this.url + "/employee-server/employee");
  }

  public deleteEmployee(deleteEmp: any) {
    return this.http.delete(this.url + "/employee-server/employee/delete", {responseType: 'text' as 'json'});
  }

  public getCustomers() : Observable<Customers[]>{
    return this.http.get<Customers[]>(this.url + "/email-server/customers")
  }
  public sendmail1(bill : any){
    return this.http.post(this.url + "/email-server/custprod/sendmail", bill, {responseType: 'text' as 'json'});
  }


}
