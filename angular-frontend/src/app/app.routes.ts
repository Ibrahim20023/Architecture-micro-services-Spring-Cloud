import { Routes } from '@angular/router';
import {CustomersComponent} from "./customers/customers.component";
import {ProductsComponent} from "./products/products.component";
import {OrdersComponent} from "./orders/orders.component";
import {OrderDetailsComponent} from "./order-details/order-details.component";

export const routes: Routes = [
  { path : "customers" , component : CustomersComponent},
  { path : "products", component : ProductsComponent},
  { path : "bills/:customerId", component : OrdersComponent},
  { path : "bill-details/:billId", component : OrderDetailsComponent}

];
