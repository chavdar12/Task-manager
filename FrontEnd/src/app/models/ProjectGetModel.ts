import {AccountModel} from "./AccountModel";
import {TaskModel} from "./TaskModel";

export interface ProjectGetModel {
  id: number;
  key: string;
  title: string;
  owner: AccountModel;
  tasks: TaskModel[];
}
