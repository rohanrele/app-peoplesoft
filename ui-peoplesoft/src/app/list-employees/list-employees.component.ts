import { Component, OnInit } from '@angular/core';
import { Employee } from '../bo/employee';
import { RestEmployeeService } from '../service/rest-employee.service';

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit {
  // employees = [
  //   new Employee(
  //     1,
  //     'Rohan',
  //     'Rele',
  //     'rohanrele@gmail.com',
  //     'Canada',
  //     'British Columbia',
  //     'Victoria'
  //   ),
  //   new Employee(
  //     2,
  //     'Shanaaya',
  //     'Rele',
  //     'shanaayarele@gmail.com',
  //     'Canada',
  //     'Ontario',
  //     'Toronto'
  //   )
  // ];

  employees = null;
  infoMessage = null;

  constructor(private restEmployeeService: RestEmployeeService) { }

  ngOnInit(): void {
    this.restEmployeeService.getAllEmployees().subscribe(
      response => this.handleSuccessGetAllEmployees(response),
      error => this.handleErrorGetAllEmployees(error)
    );
    this.infoMessage = "Loading...";
  }

  handleSuccessGetAllEmployees(response){
    this.employees = response;
    if(this.employees == null || this.employees.length == 0){
      this.infoMessage = "No data."
    }
  }

  handleErrorGetAllEmployees(error){
    this.infoMessage = "Some error has occured.";
  }
}
