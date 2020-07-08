import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../bo/employee';
import { RestEmployeeService } from '../service/rest-employee.service';
import { RestGeographyService } from '../service/rest-geography.service';

import { Country } from '../bo/country';
import { Province } from '../bo/province';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  employeeId: number;
  employee: Employee;
  errorMessage: string;
  countries: Country[];
  provinces: Province[];

  constructor(private route: ActivatedRoute, private restEmployeeService: RestEmployeeService, private restGeographyService: RestGeographyService) { }

  ngOnInit(): void {
    // get selected employee id from employee list page
    this.employeeId = this.route.snapshot.params['id'];
    // get all countries to show in drop down
    this.restGeographyService.getAllCountries().subscribe(response => this.handleSuccessGetAllCountries(response), error => this.handleErrorGetAllCountries(error));
    // get employee depending on selected employee id
    this.restEmployeeService.getEmployee(this.employeeId).subscribe(response => this.handleSuccessGetEmployee(response), error => this.handleErrorGetEmployee(error));
  }

  updateEmployee(): void {

  }

  getProvincesByCountryId():void{
    // get provinces by country id selected from country drop down
    this.restGeographyService.getProvincesByCountryId(this.employee.countryId).subscribe(response => this.handleSuccessGetProvincesByCountryId(response), error => this.handleErrorGetProvincesByCountryId(error));
  }

  handleSuccessGetEmployee(response: Employee): void {
    this.employee = response;

    if (this.employee == null) {
      this.errorMessage = "Employee doesnt exit."
    }else{
      // get provinces depending on employee's country id
      this.restGeographyService.getProvincesByCountryId(this.employee.countryId).subscribe(response => this.handleSuccessGetProvincesByCountryId(response), error => this.handleErrorGetProvincesByCountryId(error));
    }
  }

  handleErrorGetEmployee(error: any): void {
    this.errorMessage = "Some error has occured.";
  }

  handleSuccessGetProvincesByCountryId(response: Province[]): void {
    this.provinces = response;
    if (this.provinces == null || this.provinces.length == 0) {
      this.errorMessage = "Provinces doesnt exit."
    }
  }

  handleErrorGetProvincesByCountryId(error: any): void {
    this.errorMessage = "Some error has occured.";
  }

  handleSuccessGetAllCountries(response: Country[]): void {
    this.countries = response;
    if (this.countries == null || this.countries.length == 0) {
      this.errorMessage = "Countries doesnt exit."
    }
  }

  handleErrorGetAllCountries(error: any): void {
    this.errorMessage = "Some error has occured.";
  }
}
