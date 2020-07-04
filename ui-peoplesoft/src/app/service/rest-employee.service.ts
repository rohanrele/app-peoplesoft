import { Injectable } from '@angular/core';
import { Employee } from '../bo/employee';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RestEmployeeService {

  constructor(private httpClient: HttpClient) { }

  getAllEmployees(){
    return this.httpClient.get<Employee[]>('http://localhost:8081/employee');
  }
}
