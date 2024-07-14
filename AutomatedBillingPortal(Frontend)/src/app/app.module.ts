import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegisterpageComponent } from './registerpage/registerpage.component';
import { CustomerpageComponent } from './customerpage/customerpage.component';
import { ProductbillingpageComponent } from './productbillingpage/productbillingpage.component';
import { EmployeemgntpageComponent } from './employeemgntpage/employeemgntpage.component';
import { SendmailformComponent } from './sendmailform/sendmailform.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginpageComponent,
    LandingPageComponent,
    RegisterpageComponent,
    CustomerpageComponent,
    ProductbillingpageComponent,
    EmployeemgntpageComponent,
    SendmailformComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
