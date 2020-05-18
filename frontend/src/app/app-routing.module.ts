import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeComponent } from './employee/employee.component';
import {AddEmployeeComponent} from "./add-employee/add-employee.component";
import {LoginComponent} from "./authentication/login/login.component";
import {LogoutComponent} from "./authentication/logout/logout.component";
import {AuthGuardService} from "./authentication/auth-guard.service";
import {ElkoproductsComponent} from "./elkoproducts/elkoproducts.component";

const routes: Routes = [
  { path:'', component: EmployeeComponent, canActivate:[AuthGuardService]},
  { path:'addemployee', component: AddEmployeeComponent, canActivate:[AuthGuardService]},
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate:[AuthGuardService] },
  { path: 'elko-products', component: ElkoproductsComponent, canActivate:[AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
