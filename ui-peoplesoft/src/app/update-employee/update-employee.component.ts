import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../bo/employee';
import { RestEmployeeService } from '../service/rest-employee.service';
import { RestGeographyService } from '../service/rest-geography.service';

import { Country } from '../bo/country';
import { Province } from '../bo/province';
import { City } from '../bo/city';

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
  cities: City[];

  constructor(private router: Router, private route: ActivatedRoute, private restEmployeeService: RestEmployeeService, private restGeographyService: RestGeographyService) { }

  ngOnInit(): void {
    // get selected employee id from employee list page
    this.employeeId = this.route.snapshot.params['id'];
    // get all countries to show in drop down
    this.restGeographyService.getAllCountries().subscribe(response => this.handleSuccessGetAllCountries(response), error => this.handleError(error));
    // get employee depending on selected employee id
    this.restEmployeeService.getEmployee(this.employeeId).subscribe(response => this.handleSuccessGetEmployee(response), error => this.handleError(error));
  }

  updateEmployee(): void {
    if(this.employee.firstName.trim() === '' || this.employee.lastName.trim() === '' || this.employee.emailId.trim() === ''){
      this.errorMessage = "First Name, Last Name and Email Id has to be filled.";
      return;
    }
    this.restEmployeeService.updateEmployee(this.employeeId, this.employee).subscribe(response => this.handleSuccessUpdateEmployee(response), error => this.handleError(error));
  }

  // get provinces by country id selected by user by changing country drop down
  getProvincesByCountryId():void{
    // reset province drop down as well as employee's province
    this.provinces = null; this.employee.provinceId = -1;
    // reset city drop down as well as employee's city
    this.cities = null; this.employee.cityId = -1;
    // dont fetch provinces if user selects default value in country drop down
    if(this.employee.countryId != -1){
      // fetch provinces by country id
      this.restGeographyService.getProvincesByCountryId(this.employee.countryId).subscribe(response => this.handleSuccessGetProvincesByCountryId(response), error => this.handleError(error));
    }
  }

  // get cities by province id selected by user by changing province drop down
  getCitiesByProvinceId():void{
    // reset city drop down as well as employee's city
    this.cities = null; this.employee.cityId = -1;
    // dont fetch cities if user selects default value in province drop down
    if(this.employee.provinceId != -1){
      // fetch cities by province id
      this.restGeographyService.getCitiesByProvinceId(this.employee.provinceId).subscribe(response => this.handleSuccessGetCitiesByProvinceId(response), error => this.handleError(error));
    }
  }

  handleSuccessGetEmployee(response: Employee): void {
    this.employee = response;

    if (this.employee == null) { // should not happen
      this.errorMessage = "Employee doesnt exit."
    }else{
      // get provinces depending on employee's country id
      this.restGeographyService.getProvincesByCountryId(this.employee.countryId).subscribe(response => this.handleSuccessGetProvincesByCountryId(response), error => this.handleError(error));
      // get cities depending on employee's province id
      this.restGeographyService.getCitiesByProvinceId(this.employee.provinceId).subscribe(response => this.handleSuccessGetCitiesByProvinceId(response), error => this.handleError(error));
    }
  }

  handleSuccessUpdateEmployee(response: Employee): void {
    this.employee = response;
    this.router.navigate(['listEmployees']);
  }

  handleSuccessGetCitiesByProvinceId(response: City[]): void {
    this.cities = response;
  }

  handleSuccessGetProvincesByCountryId(response: Province[]): void {
    this.provinces = response;
  }

  handleSuccessGetAllCountries(response: Country[]): void {
    this.countries = response;
  }

  handleError(error: any): void {
    this.errorMessage = "Some error has occured.";
  }
}
