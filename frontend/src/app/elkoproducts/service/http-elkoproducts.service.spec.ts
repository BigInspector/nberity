import { TestBed } from '@angular/core/testing';

import { HttpElkoproductsService } from './http-elkoproducts.service';

describe('HttpElkoproductsService', () => {
  let service: HttpElkoproductsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpElkoproductsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
