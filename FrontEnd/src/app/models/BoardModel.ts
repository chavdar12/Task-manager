import {AccountModel} from "./AccountModel";

export interface BoardModel {
  id: number;
  name: string;
  owner: AccountModel;
  members: AccountModel[];
}
