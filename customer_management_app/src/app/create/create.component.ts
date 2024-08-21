import { Component, OnInit } from '@angular/core';

import { CustomerService } from '../service/customer.service';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { Task } from '../model/Task';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  task: Task = {}
  Id: any = ""

  constructor(private service: CustomerService, private router: Router) { }

  ngOnInit(): void {
    this.Id = this.service.getEmail();
  }

  function1() {
    console.log(this.task);
    this.service.addTask(this.Id, this.task).subscribe(
      response => {
        console.log("After Save" + response);
        this.router.navigate(['home'])
        alert("succcessfully created");
      });
  }
} 