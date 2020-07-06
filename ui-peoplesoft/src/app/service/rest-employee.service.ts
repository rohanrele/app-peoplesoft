import { Injectable } from '@angular/core';
import { Employee } from '../bo/employee';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestEmployeeService {

  constructor(private httpClient: HttpClient) { }

  getAllEmployees():Observable<Employee[]>{
    return this.httpClient.get<Employee[]>('http://localhost:8081/employee');
  }

  getEmployee(employeeId:string):Observable<Employee>{
    return this.httpClient.get<Employee>(`http://localhost:8081/employee/${employeeId}`);
  }
}
