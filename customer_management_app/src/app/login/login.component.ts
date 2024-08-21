import { Component } from '@angular/core';
import { CustomerService } from '../service/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  feedback: any = {
    'emailId': "",
    'password': ""
  };

  constructor(private service: CustomerService, private route: Router) { }


  logIn(feedback: any) {
    this.service.login(feedback.value).subscribe((data: any) => {
      this.service.loginUser(data.secretKeyToken.token);
      this.service.captureEmail(data.user.emailId)
      this.route.navigateByUrl('home')
    });
  }
}



