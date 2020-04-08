import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { HttpService } from '../http.service';

export interface StockDetailsDto {
  id: number;
  name: string;
  currentPrice: number;
  lastUpdated: Date;
}


@Component({
  selector: 'app-stock-details',
  templateUrl: './stock-details.component.html',
  styleUrls: ['./stock-details.component.css']
})

export class StockDetailsComponent implements OnInit {

  dataList: any;

  constructor(private _http: HttpService) { }

  displayedColumns: string[] = ['id', 'name', 'price', 'lastUpdated'];
  dataSource = new MatTableDataSource<StockDetailsDto>(this.dataList);
 

ngOnInit(): void {
  this._http.getStocks().subscribe(dataList => {
    this.dataList = dataList;
    console.log(dataList);
    this.dataSource = new MatTableDataSource<StockDetailsDto>(this.dataList.stockDetailsList);
  });
  }

}
