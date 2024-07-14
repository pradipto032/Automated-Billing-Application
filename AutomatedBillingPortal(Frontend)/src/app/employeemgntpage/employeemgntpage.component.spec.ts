import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeemgntpageComponent } from './employeemgntpage.component';

describe('EmployeemgntpageComponent', () => {
  let component: EmployeemgntpageComponent;
  let fixture: ComponentFixture<EmployeemgntpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeemgntpageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeemgntpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
