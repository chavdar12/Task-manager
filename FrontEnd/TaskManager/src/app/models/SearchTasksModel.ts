import {AccountModel} from "./AccountModel";

export interface SearchTasksModel {
  title: string;
  type: string;
  assignedTo: AccountModel;
}
