import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, of, pipe } from 'rxjs';
import { map, catchError, tap } from 'rxjs/operators';
import { Person } from './person';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private baseUrl = 'http://localhost:8080/angular-jee-backend/jaxrs';

  constructor(private http: HttpClient) {
  }

  get(id: number): Observable<Person> {
    console.log("Método get() chamado.");
    return this.http
      .get<Person>(`${this.baseUrl}/Person/${id}`, {headers: this.getHeaders()})
      .pipe(tap(_ => console.log(`fetched cases id=${id}`)),
        catchError(this.handleError<Person>(`getPersonById id=${id}`))
      );
  }

  getAll(): Observable<Person[]> {
    console.log("Método getAll() chamado.");
    return this.http
      .get<Person[]>(`${this.baseUrl}/Person`, {headers: this.getHeaders()})
      .pipe(tap(_ => console.log('fetched cases')),
          catchError(this.handleError('getAll', []))
      );
  }

  save(person: Person): Observable<any> {
    console.log("Método save() chamado.");
    return this.http.put(`${this.baseUrl}/Person`, JSON.stringify(person), {headers: this.getHeaders()})
      .pipe(tap(_ => console.log(`updated person id=${person.id}`)),
        catchError(this.handleError<any>('updatePerson'))
      );
  }

  private getHeaders() {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    headers.append('Accept', 'application/json');
    //headers.append('Content-Type', 'application/json');
    return headers;
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}
}

