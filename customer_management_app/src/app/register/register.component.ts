import { Component } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { CustomerService } from '../service/customer.service';
import { User } from '../model/User';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  user: User = {}
  constructor(private router: Router, private userTaskSer: CustomerService) { }




  function1() {
    this.userTaskSer.registerUser(this.user).subscribe(
      response => {
        alert("|| User successfully saved ||");
        this.router.navigate(['login'])
      }
    ), (err: HttpErrorResponse) => {
      if (err.status == 409) {
        alert("Registration failed! User Already Exists");
        console.log(err.message);
      } else {
        alert("Server Site Problem");
        console.log(err.message);
      }
    }

  }

}
