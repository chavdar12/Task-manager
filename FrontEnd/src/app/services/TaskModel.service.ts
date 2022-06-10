import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TaskModel} from "../models/TaskModel";
import {Observable} from "rxjs";
import {SearchTasksModel} from "../models/SearchTasksModel";

@Injectable({
  providedIn: 'root'
})
export class TaskModelService {

  private baseUrl: string = "http://localhost:8080/task";

  constructor(private http: HttpClient) {
  }

  getTasks(): Observable<TaskModel[]> {
    return this.http.get<TaskModel[]>(this.baseUrl + "/all");
  }

  createTask(task: TaskModel): Observable<TaskModel> {
    return this.http.post<TaskModel>(this.baseUrl + "/create", task);
  }

  updateTask(task: TaskModel): Observable<TaskModel> {
    return this.http.patch<TaskModel>(this.baseUrl + "/update", task);
  }

  deleteTask(id: number): Observable<number> {
    return this.http.delete<number>(this.baseUrl + "/delete/" + id);
  }

  searchTasks(search: SearchTasksModel): Observable<TaskModel[]> {
    return this.http.post<TaskModel[]>(this.baseUrl + "/search", search);
  }
}
