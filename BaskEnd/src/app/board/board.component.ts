import {Component, OnInit} from '@angular/core';
import {BoardGetModel} from "../models/BoardGetModel";
import {BoardModelService} from "../services/BoardModel.service";
import {AccountModelService} from "../services/AccountModel.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BoardModel} from "../models/BoardModel";
import {AccountModel} from "../models/AccountModel";

@Component({
  selector: 'app-board', templateUrl: './board.component.html', styleUrls: ['./board.component.css']
})
export class BoardComponent implements OnInit {
  allBoards: BoardGetModel[] = [];
  allAccounts: AccountModel[] = [];
  boardToBeUpdated!: BoardModel;
  showUpdateForm = false;
  createBoardForm = this.formBuilder.group({
    name: [''], owner: [], members: []
  });
  updateBoardForm!: FormGroup;

  constructor(private boardService: BoardModelService, private accountService: AccountModelService, private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.getBoards();
    this.getAccounts();
  }

  private getAccounts() {
    return this.accountService.getAccounts().subscribe((accounts: AccountModel[]) => {
      this.allAccounts = accounts;
    });
  }

  getBoards() {
    this.boardService.getBoards().subscribe((boards: BoardGetModel[]) => {
      this.allBoards = boards;
    });
  }

  onSelect(board: BoardGetModel) {
    this.showUpdateForm = true;

    this.boardToBeUpdated = board;

    this.updateBoardForm = this.formBuilder.group({
      name: board.name, owner: [], members: []
    })
  }

  onDelete(id: number) {
    this.boardService.deleteBoard(id).subscribe(() => {
      this.getBoards();
    });
  }

  onCreateBoard() {
    this.boardService.createBoard(this.createBoardForm.value).subscribe(() => {
      this.getBoards();
    }, () => {
      alert('Board error');
    })
  }

  onUpdateBoard() {
    this.boardToBeUpdated.name = this.updateBoardForm.value.name;
    this.boardToBeUpdated.owner = this.updateBoardForm.value.owner;
    this.boardToBeUpdated.members = this.updateBoardForm.value.members;

    this.boardService.updateBoard(this.boardToBeUpdated).subscribe(() => {
      this.getBoards();
    });
    this.showUpdateForm = false;
  }
}
