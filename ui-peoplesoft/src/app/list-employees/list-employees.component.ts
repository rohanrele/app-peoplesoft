import { Component, OnInit } from '@angular/core';
import { Employee } from '../bo/employee';
import { RestEmployeeService } from '../service/rest-employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit {

  employees = null;
  infoMessage = null;

  constructor(private restEmployeeService: RestEmployeeService, private router: Router) { }

  ngOnInit(): void {
    this.restEmployeeService.getAllEmployees().subscribe(
      response => this.handleSuccessGetAllEmployees(response),
      error => this.handleErrorGetAllEmployees(error)
    );
    this.infoMessage = 'Loading...';
  }

  handleSuccessGetAllEmployees(response:Employee[]): void{
    this.employees = response;
    if(this.employees === null || this.employees.length === 0){
      this.infoMessage = 'No data.';
    }
  }

  handleErrorGetAllEmployees(error:any): void{
    this.infoMessage = 'Some error has occured.';
  }

  updateEmployee(employeeId:number): void{
    this.router.navigate(['updateEmployee', employeeId]);
  }
}
