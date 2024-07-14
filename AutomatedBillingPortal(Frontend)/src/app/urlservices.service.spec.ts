import { TestBed } from '@angular/core/testing';

import { UrlservicesService } from './urlservices.service';

describe('UrlservicesService', () => {
  let service: UrlservicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UrlservicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
