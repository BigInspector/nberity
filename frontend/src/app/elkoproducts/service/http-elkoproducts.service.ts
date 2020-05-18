import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

export class ElkoProduct {

  constructor(
    public id : number,
    public elkoCode : number,
    public name : string,
    public manufacturerCode : string,
    public vendorName : string,
    public vendorCode : string,
    public catalog : string,
    public quantity : string,
    public price,
    public discountPrice,
    public imagePath : string,
    public thumbnailImagePath : string,
    public fullDsc : string,
    public currency : string,
    public httpDescription : string,
    public packagingQuantity : number,
    public warranty : string,
    public eanCode : string,
    public obligatoryKit : number,
    public reservedQuantity : number,
    public promDate : number,
    public promQuant : number,
    public quantityForPrice2 : string,
    public price2 : number,
    public lotNumber : string,
    public copyrightTax : number,
    public incomingQuantity : number,
  ) {}

}

export class ElkoProductsPage {
  content: ElkoProduct[];
  totalPages : number;
  totalElements : number;
  last : boolean;
  size : number ;
  first : boolean ;
  sort : string ;
  numberOfElements : number ;
}

@Injectable({
  providedIn: 'root'
})
export class HttpElkoproductsService {

  private pageSize = 20;

  constructor(private httpClient: HttpClient) { }

  getLatestElkoProducts() {
    console.log("test get employees");
    return this.httpClient.get<ElkoProduct[]>('http://localhost:8080/products/latest-elko-products');
  }

  getElkoProductsByPageNumber(page:number): Observable<ElkoProductsPage>{

    return this.httpClient.get<ElkoProductsPage>('http://localhost:8080/products/get-page?page=' + page + '&size=' + this.pageSize)
      .pipe(
        map(response => {
          const data = response;
          console.log(data);
          return data ;
        }));
  }
}
