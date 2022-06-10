import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ProjectGetModel} from "../models/ProjectGetModel";
import {Observable} from "rxjs";
import {ProjectModel} from "../models/ProjectModel";

@Injectable({providedIn: 'root'})
export class ProjectModelService {

  private baseUrl: string = "http://localhost:8080/project";

  constructor(private http: HttpClient) {
  }

  getProjects(): Observable<ProjectGetModel[]> {
    return this.http.get<ProjectGetModel[]>(this.baseUrl + "/all");
  }

  createProject(project: ProjectModel): Observable<ProjectModel> {
    return this.http.post<ProjectModel>(this.baseUrl + "/create", project);
  }

  updateProject(project: ProjectModel): Observable<ProjectModel> {
    return this.http.patch<ProjectModel>(this.baseUrl + "/update", project);
  }

  deleteProject(id: number): Observable<number> {
    return this.http.delete<number>(this.baseUrl + "/delete/" + id);
  }
}
