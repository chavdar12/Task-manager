import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {BoardGetModel} from "../models/BoardGetModel";
import {Observable} from "rxjs";
import {BoardModel} from "../models/BoardModel";

@Injectable({
  providedIn: "root"
})
export class BoardModelService {
  private baseUrl: string = "http://localhost:8080/board";

  constructor(private http: HttpClient) {
  }

  getBoards(): Observable<BoardGetModel[]> {
    return this.http.get<BoardGetModel[]>(this.baseUrl + "/all");
  }

  createBoard(board: BoardModel): Observable<BoardModel> {
    return this.http.post<BoardModel>(this.baseUrl + "/create", board);
  }

  updateBoard(board: BoardModel): Observable<BoardModel> {
    return this.http.patch<BoardModel>(this.baseUrl + "/update", board);
  }

  deleteBoard(id: number): Observable<number> {
    return this.http.delete<number>(this.baseUrl + "/delete/" + id);
  }
}
