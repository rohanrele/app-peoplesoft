import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from '../bo/country';
import { Province } from '../bo/province';
import { City } from '../bo/city';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class RestGeographyService {

  constructor(private httpClient: HttpClient) { }

  getAllCountries():Observable<Country[]>{
    return this.httpClient.get<Country[]>('http://localhost:8080/country');
  }

  getProvincesByCountryId(countryId:number):Observable<Province[]>{
    return this.httpClient.get<Province[]>(`http://localhost:8080/country/${countryId}/province`);
  }

  getCitiesByProvinceId(provinceId:number):Observable<City[]>{
    return this.httpClient.get<City[]>(`http://localhost:8080/province/${provinceId}/city`);
  }
}
