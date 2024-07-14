import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendmailformComponent } from './sendmailform.component';

describe('SendmailformComponent', () => {
  let component: SendmailformComponent;
  let fixture: ComponentFixture<SendmailformComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendmailformComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SendmailformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
