import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerService } from './customer.service';

@Injectable()
export class VerifiyAdminInterceptor implements HttpInterceptor {

  constructor(private authService: CustomerService) {}

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const authToken = this.authService.getToken();
    req = req.clone({
      setHeaders: {
          Authorization: "Bearer " + authToken
      }
  });
    return next.handle(req);
  }
}
