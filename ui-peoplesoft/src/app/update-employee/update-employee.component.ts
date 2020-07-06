import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../bo/employee';
import { RestEmployeeService } from '../service/rest-employee.service';
import { Country } from '../bo/country';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  employeeId: string;
  employee: Employee;
  errorMessage: string;
  countries = [
    new Country(1, 'India'),
    new Country(8, 'Canada')
  ];

  constructor(private route: ActivatedRoute, private restEmployeeService: RestEmployeeService) { }

  ngOnInit(): void {
    this.employeeId = this.route.snapshot.params['id'];
    this.restEmployeeService.getEmployee(this.employeeId).subscribe(response => this.handleSuccessGetEmployee(response), error => this.handleErrorGetEmployee(error));
  }

  updateEmployee(): void {

  }

  handleSuccessGetEmployee(response: Employee): void {
    this.employee = response;
    if (this.employee == null) {
      this.errorMessage = "Employee doesnt exit."
    }
  }

  handleErrorGetEmployee(error: any): void {
    this.errorMessage = "Some error has occured.";
  }
}
