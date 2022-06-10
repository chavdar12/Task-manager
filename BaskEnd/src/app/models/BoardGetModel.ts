import {AccountModel} from "./AccountModel";
import {TaskModel} from "./TaskModel";
import {ProjectModel} from "./ProjectModel";

export interface BoardGetModel {
  id: number;
  name: string;
  owner: AccountModel;
  members: AccountModel[];
  tasks: TaskModel[];
  projects: ProjectModel[];
}
