import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { RegisterpageComponent } from './registerpage/registerpage.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { CustomerpageComponent } from './customerpage/customerpage.component';
import { ProductbillingpageComponent } from './productbillingpage/productbillingpage.component';
import { EmployeemgntpageComponent } from './employeemgntpage/employeemgntpage.component';
import { SendmailformComponent } from './sendmailform/sendmailform.component';

const routes: Routes = [
  {path: "", component: LoginpageComponent},
  {path: "aba/login", component: LoginpageComponent},
  {path: "aba/register", component: RegisterpageComponent},
  {path: "aba/homepage", component: LandingPageComponent},
  {path: "aba/customerregister", component: CustomerpageComponent},
  {path: "aba/productbilling", component: ProductbillingpageComponent},
  {path: "aba/employeemanage", component: EmployeemgntpageComponent},
  {path: "aba/sendmail", component: SendmailformComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
