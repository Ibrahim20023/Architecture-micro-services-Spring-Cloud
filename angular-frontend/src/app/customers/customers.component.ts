import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {CommonModule, NgForOf} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [HttpClientModule, CommonModule],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  customers : any;
  constructor(private http: HttpClient, private router: Router) {
  }
  ngOnInit() {
    this.http.get("http://localhost:8888/CLIENT-SERVICE/customers")
      .subscribe({
        next : data => {
          this.customers = data;
        },
        error : err => {
          console.log(err);
        }
      })
  }

  getOrders(c: any) {
    this.router.navigateByUrl("/bills/"+ c.id);
  }
}
