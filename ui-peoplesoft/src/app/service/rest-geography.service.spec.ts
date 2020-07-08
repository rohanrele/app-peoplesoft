import { TestBed } from '@angular/core/testing';

import { RestGeographyService } from './rest-geography.service';

describe('RestGeographyService', () => {
  let service: RestGeographyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestGeographyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
