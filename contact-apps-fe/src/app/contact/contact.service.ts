import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
import { map, Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class ContactService {
  private baseUri = 'http://localhost:8081/api/public/contact'

  constructor (
    private httpClient: HttpClient,
    private message: MessageService
  )
  {

  }

  public search(searchParams?: any): Observable<any> {
    let httpParams = new HttpParams();
    console.log(searchParams)
    for (const param in searchParams) {
      console.log(param, searchParams[param])
      httpParams = httpParams.set(param, searchParams[param])
    }

    return this.httpClient
      .get<any>(this.baseUri, {
        headers: this.getHttpHeaders(),
        params: httpParams,
      })
      .pipe(
        map((r) => {return r;})
      );
  }

  private getHttpHeaders(): HttpHeaders {
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.set('Content-Type', 'application/json');
    return httpHeaders;
  }
}
