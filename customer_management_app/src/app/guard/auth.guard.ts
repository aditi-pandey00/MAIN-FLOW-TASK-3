import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { CustomerService } from '../service/customer.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate {
constructor(private service:CustomerService , private router:Router){

}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.service.isLoggedIn()){
        return true;
      }
      this.router.navigate(['login'])
      return false;
  }
  
}