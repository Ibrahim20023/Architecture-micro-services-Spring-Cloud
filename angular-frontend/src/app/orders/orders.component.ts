import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit{
  bills : any;
  customerId!:number;
  constructor(private http: HttpClient,private router: Router, private route: ActivatedRoute) {
    this.customerId = route.snapshot.params['customerId'];
  }

  ngOnInit() {
    this.http.get("http://localhost:8888/BILLING-SERVICE/bills/search/byCustomerId?projection=fullBill&customerId="+this.customerId)
      .subscribe({
        next : data => {
          this.bills = data;
        },
        error : err => {
          console.log(err);
        }
      })
  }

  getOrderDetails(o: any) {
    this.router.navigateByUrl("/bill-details/"+ o.id);
  }
}
