import { Injectable } from '@angular/core';
import { Employee } from '../bo/employee';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestEmployeeService {

  constructor(private httpClient: HttpClient) { }

  getAllEmployees():Observable<Employee[]>{
    return this.httpClient.get<Employee[]>('http://localhost:8081/employee', {headers : this.getAuthorisationHeaderForBasicAuthentication()});
  }

  getEmployee(employeeId:number):Observable<Employee>{
    return this.httpClient.get<Employee>(`http://localhost:8081/employee/${employeeId}`, {headers : this.getAuthorisationHeaderForBasicAuthentication()});
  }

  updateEmployee(employeeId:number, employee: Employee):Observable<Employee>{
    return this.httpClient.put<Employee>(`http://localhost:8081/employee/${employeeId}`, employee, {headers : this.getAuthorisationHeaderForBasicAuthentication()});
  }

  getAuthorisationHeaderForBasicAuthentication():HttpHeaders{
    let username = 'rseuser1';
    let password = 'rseuser1password'
    let headerValue = 'Basic ' + window.btoa(username + ':' + password);
    
    return new HttpHeaders({Authorization: headerValue});
  }
}
