import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  // [M] server
  private baseUrl = 'http://localhost:8080/api/products';

  constructor(private httpClient: HttpClient) { }

  // [M] Return an ocservable Map the JSON from Spring Data REST to Product array
  getProductList(): Observable<Product[]> {
    return this.httpClient.get<GetResponse>(this.baseUrl).pipe(
      map(response => response._embedded.products)
    );
  }
}

// [M] Unwraps the JSON from Spring Data REST _embadded entry
interface GetResponse {
  _embedded: {
    products: Product[];
  }
}
