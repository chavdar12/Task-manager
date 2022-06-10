import {BoardModel} from "./BoardModel";
import {AccountModel} from "./AccountModel";
import {ProjectModel} from "./ProjectModel";

export interface TaskModel {
  id: number;
  title: string;
  project: ProjectModel;
  board: BoardModel;
  type: string;
  status: string;
  description: string;
  reportedBy: AccountModel;
  assignedTo: AccountModel;
  priority: string;
  storyPoints: number;
}
