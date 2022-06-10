import {AccountModel} from "./AccountModel";

export interface ProjectModel {
  id: number;
  key: string;
  title: string;
  owner: AccountModel;
}
