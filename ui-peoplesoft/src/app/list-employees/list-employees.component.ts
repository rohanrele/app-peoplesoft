import { Component, OnInit } from '@angular/core';
import { Employee } from '../bo/employee';

@Component({
  selector: 'app-list-employees',
  templateUrl: './list-employees.component.html',
  styleUrls: ['./list-employees.component.css']
})
export class ListEmployeesComponent implements OnInit {
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
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
