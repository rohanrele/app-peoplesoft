import { Component, OnInit } from '@angular/core';


export class Employee {
  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public emailId: string,
    public countryName: string,
    public provinceName: string,
    public cityName: string) {
  }
}

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit {
  title = 'ListEmployeesComponent Title'
  employees = [
    new Employee(
      1,
      'Rohan',
      'Rele',
      'rohanrele@gmail.com',
      'Canada',
      'British Columbia',
      'Victoria'
    ),
    new Employee(
      2,
      'Shanaaya',
      'Rele',
      'shanaayarele@gmail.com',
      'Canada',
      'Ontario',
      'Toronto'
    )
  ]

  constructor() { }

  ngOnInit(): void {
  }

}
