import { Component, OnInit } from '@angular/core';
//import { Customer } from '../model/Task';
import { CustomerService } from '../service/customer.service';
import { Router } from '@angular/router';
import { User } from '../model/User';
import { Task } from '../model/Task';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {


  taskId: string = "";
  task: Task = {};

  constructor(private myService: CustomerService, private router: Router) { }

  ngOnInit(): void {
    this.getUserByEmailId();
  }

  getUserByEmailId() {
    this.taskId = this.myService.taskId;
    this.myService.getTaskByEmailTaskId(this.taskId).subscribe(data => this.task = data);
    console.log(this.task);
  }

  updateTask() {
    this.myService.updateTask(this.task).subscribe();
    alert("updated the record successfully")
    this.router.navigate(['home'])


  }
}
