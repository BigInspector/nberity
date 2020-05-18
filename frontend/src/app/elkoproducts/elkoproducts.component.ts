import { Component, OnInit } from '@angular/core';
import {ElkoProduct, ElkoProductsPage, HttpElkoproductsService} from "./service/http-elkoproducts.service";

@Component({
  selector: 'app-elkoproducts',
  templateUrl: './elkoproducts.component.html',
  styleUrls: ['./elkoproducts.component.css']
})
export class ElkoproductsComponent implements OnInit {
  title = 'Elko Products list';
  public elkoProductPage : ElkoProductsPage;
  public selectedPageNumber : number;
  public productData: ElkoProduct[];

  // Pagination parameters.
  p: number = 1;
  count: number = 50;

  constructor(private httpClientService: HttpElkoproductsService) {
    console.log('Application loaded. Initializing data.');
  }

  getElkoProductsPageByPageNumber(page: number) {
    this.httpClientService.getElkoProductsByPageNumber(this.p).subscribe(
      page => {
        this.elkoProductPage = page;
      }
    );
  }

  onSelect(page: number): void {
    console.log("selected page : "+page);
    this.selectedPageNumber=page;
    this.getElkoProductsPageByPageNumber(page);
  }

  ngOnInit(): void {
    this.getElkoProductsPageByPageNumber(this.p);
  }
}
