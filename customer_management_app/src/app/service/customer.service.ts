import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { Task } from '../model/Task';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {


  taskId: string = "";
  constructor(private httpClient: HttpClient) { }



  login(user: User): Observable<any> {
    return this.httpClient.post('http://localhost:8081/api/v1/login', user);
  }

  captureEmail(email: any) {
    localStorage.setItem("email", email);
    return true;
  }

  removeEmail() {
    localStorage.removeItem('email');
    return true;
  }

  getEmail() {
    return localStorage.getItem('email');
  }




  loginUser(token: any) {
    localStorage.setItem('token', token);
    return true;
  }

  registerUser(user: any) {
    return this.httpClient.post<any>("http://localhost:8081/api/v1/save", user);
  }

  logout() {
    localStorage.removeItem('token');
    return true;
  }

  getToken() {
    return localStorage.getItem('token');
  }

  //to check that the user is logged in or not !!
  isLoggedIn() {
    let token = localStorage.getItem('token');
    if (token == undefined || token === '' || token == null) {
      return false;
    } else {
      return true;
    }
  }




  getAllTasksOfUser(email: any): Observable<any> {
    return this.httpClient.get('http://localhost:8081/api/v1/task/getAllTasksOfUser/' + email);
  }

  getTaskByEmailTaskId(taskId: any): Observable<any> {
    const email = this.getEmail();
    return this.httpClient.get("http://localhost:8081/api/v1/task/getByTaskId/" + email + "/" + taskId);
  }

  getUserByEmailId(email: any): Observable<any> {
    return this.httpClient.get('http://localhost:8081/api/v1/task/getUser/' + email);
  }

  getTaskId(id: string) {
    this.taskId = id;
  }

  addTask(emailId: any, task: Task) {
    return this.httpClient.put<Task>("http://localhost:8081/api/v1/task/addTask/" + emailId, task);
  }

  updateTask(task: Task) {
    const email = this.getEmail();
    const url = "http://localhost:8081/api/v1/task/updateTask/" + email;
    // Make an HTTP Put request
    return this.httpClient.put(url, task);
  }

  deleteTask(id: any) {
    const email = this.getEmail();
    const url = "http://localhost:8081/api/v1/task/deleteTaskByTaskId/" + email + "/" + id;
    return this.httpClient.delete(url);
  }
}
