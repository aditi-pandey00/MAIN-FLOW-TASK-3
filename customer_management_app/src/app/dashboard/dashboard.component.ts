import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../service/customer.service';
import { Router } from '@angular/router';
//import { Customer } from '../model/Task';
import { User } from '../model/User';
import { Task } from '../model/Task';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {



  tasks: Task[] = []
  user: User = {}
  Id: any;

  constructor(private service: CustomerService, private route: Router) { }

  ngOnInit(): void {

    this.Id = this.service.getEmail();
    this.getTask();
  }

  getTask() {
    this.service.getUserByEmailId(this.Id).subscribe((data) => {
      this.user = data;
    })
    this.service.getAllTasksOfUser(this.Id).subscribe((data) => {
      this.tasks = data;
    })
  }

  delete(id: any) {
    this.service.deleteTask(id).subscribe();
    alert("succcessfully deleted");
    window.location.reload();
    this.route.navigate(['home']);
  }

  update(id: any) {
    this.service.getTaskId(id);
    this.route.navigate(['update'])
  }

  logOut() {
    localStorage.removeItem('token');
    this.service.removeEmail();
    this.route.navigate(['login'])
  }

}
