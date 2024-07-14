import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductbillingpageComponent } from './productbillingpage.component';

describe('ProductbillingpageComponent', () => {
  let component: ProductbillingpageComponent;
  let fixture: ComponentFixture<ProductbillingpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductbillingpageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductbillingpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
