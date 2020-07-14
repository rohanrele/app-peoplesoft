import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from '../bo/country';
import { Province } from '../bo/province';
import { City } from '../bo/city';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class RestGeographyService {

  constructor(private httpClient: HttpClient) { }

  getAllCountries():Observable<Country[]>{
    return this.httpClient.get<Country[]>('http://localhost:8080/country', {headers : this.getAuthorisationHeaderForBasicAuthentication()});
  }

  getProvincesByCountryId(countryId:number):Observable<Province[]>{
    return this.httpClient.get<Province[]>(`http://localhost:8080/country/${countryId}/province`, {headers : this.getAuthorisationHeaderForBasicAuthentication()});
  }

  getCitiesByProvinceId(provinceId:number):Observable<City[]>{
    return this.httpClient.get<City[]>(`http://localhost:8080/province/${provinceId}/city`, {headers : this.getAuthorisationHeaderForBasicAuthentication()});
  }

  getAuthorisationHeaderForBasicAuthentication():HttpHeaders{
    let username = 'rsguser1';
    let password = 'rsguser1password'
    let headerValue = 'Basic ' + window.btoa(username + ':' + password);
    
    return new HttpHeaders({Authorization: headerValue});
  }
}
