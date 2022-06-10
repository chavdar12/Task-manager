import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {AccountModel} from "../models/AccountModel";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class AccountModelService {

  private baseUrl: string = "http://localhost:8080/account";

  constructor(private http: HttpClient) {
  }

  getAccounts(): Observable<AccountModel[]> {
    return this.http.get<AccountModel[]>(this.baseUrl + "/all");
  }

  createAccount(account: AccountModel): Observable<AccountModel> {
    return this.http.post<AccountModel>(this.baseUrl + "/create", account);
  }

  updateAccount(account: AccountModel): Observable<AccountModel> {
    return this.http.patch<AccountModel>(this.baseUrl + "/update", account);
  }

  deleteAccount(id: number): Observable<number> {
    return this.http.delete<number>(this.baseUrl + "/delete/" + id);
  }
}
