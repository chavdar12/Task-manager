import {Component, OnInit} from '@angular/core';
import {TaskModelService} from "../services/TaskModel.service";
import {AccountModelService} from "../services/AccountModel.service";
import {BoardModelService} from "../services/BoardModel.service";
import {ProjectModelService} from "../services/ProjectModel.service";
import {TaskModel} from "../models/TaskModel";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountModel} from "../models/AccountModel";
import {BoardGetModel} from "../models/BoardGetModel";
import {ProjectGetModel} from "../models/ProjectGetModel";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  allTasks: TaskModel[] = [];
  taskToBeUpdated!: TaskModel;
  allAccounts: AccountModel[] = [];
  allProjects: ProjectGetModel[] = [];
  allBoards: BoardGetModel[] = [];
  types = ['STORY', 'BUG', 'EPIC', 'NEW', 'FEATURE', 'TECHNICAL_DEBT'];
  statuses = ['NEW', 'IN_DEVELOPMENT', 'IN_QA', 'DONE'];
  priorities = ['LOW', 'MEDIUM', 'HIGH'];

  taskCreateForm = this.formBuilder.group({
    title: [''],
    project: [''],
    board: [''],
    type: [''],
    status: [''],
    description: [''],
    reportedBy: [''],
    assignedTo: [''],
    priority: [''],
    storyPoints: ['']
  })
  showUpdateForm = false;
  updateTaskForm!: FormGroup;

  constructor(private taskService: TaskModelService,
              private accountService: AccountModelService,
              private boardService: BoardModelService,
              private projectService: ProjectModelService,
              private formBuilder: FormBuilder
  ) {

  }

  ngOnInit(): void {
    this.getAllTasks();
    this.getAllAccounts();
    this.getAllProjects();
    this.getAllBoards();
  }

  getAllTasks() {
    this.taskService.getTasks().subscribe((tasks: TaskModel[]) => {
      this.allTasks = tasks;
    });
  }

  getAllAccounts() {
    this.accountService.getAccounts().subscribe((accounts: AccountModel[]) => {
      this.allAccounts = accounts;
    });
  }

  getAllProjects() {
    this.projectService.getProjects().subscribe((projects: ProjectGetModel[]) => {
      this.allProjects = projects;
    });
  }

  getAllBoards() {
    this.boardService.getBoards().subscribe((boards: BoardGetModel[]) => {
      this.allBoards = boards;
    });
  }


  onSelect(task: TaskModel) {
    this.taskToBeUpdated = task;
    this.showUpdateForm = true;
    this.updateTaskForm = this.formBuilder.group({
      title: [task.title],
      project: [task.project],
      board: [task.board],
      type: [task.type],
      status: [task.status],
      description: [task.description],
      reportedBy: [task.reportedBy],
      assignedTo: [task.assignedTo],
      priority: [task.priority],
      storyPoints: [task.storyPoints]
    });
  }

  onDelete(id: number) {
    this.taskService.deleteTask(id).subscribe(() => {
      this.getAllTasks();
    });
  }

  onCreateTask() {
    console.log(this.taskCreateForm.value);
    this.taskService.createTask(this.taskCreateForm.value).subscribe(() => {
      this.getAllTasks();
    });

  }

  onUpdateBoard() {
    this.taskService.updateTask(this.taskToBeUpdated).subscribe(() => {
      this.getAllTasks();
    });
    this.showUpdateForm = false;
  }
}
