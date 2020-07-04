import { TestBed } from '@angular/core/testing';

import { RestEmployeeService } from './rest-employee.service';

describe('RestEmployeeService', () => {
  let service: RestEmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestEmployeeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
