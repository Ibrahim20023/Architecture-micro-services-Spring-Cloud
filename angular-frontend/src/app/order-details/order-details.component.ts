import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {DatePipe, JsonPipe, NgIf, CommonModule} from "@angular/common";

@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [
    NgIf,
    JsonPipe,
    HttpClientModule,
    CommonModule,
    DatePipe
  ],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent implements OnInit{
  billDetails : any;
  billId!:number;
  constructor(private http: HttpClient,private router: Router, private route: ActivatedRoute) {
    this.billId = route.snapshot.params['billId'];
  }

  ngOnInit() {
    this.http.get("http://localhost:8888/BILLING-SERVICE/fullBill/"+this.billId)
      .subscribe({
        next : data => {
          this.billDetails = data;
        },
        error : err => {
          console.log(err);
        }
      })
  }

}
