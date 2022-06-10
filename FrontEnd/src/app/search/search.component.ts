import {Component, OnInit} from '@angular/core';
import {TaskModelService} from "../services/TaskModel.service";
import {TaskModel} from "../models/TaskModel";
import {FormBuilder} from "@angular/forms";
import {SearchTasksModel} from "../models/SearchTasksModel";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  allTasks: TaskModel[] = [];
  searchResults: TaskModel[] = [];
  types = ['STORY', 'BUG', 'EPIC', 'NEW', 'FEATURE', 'TECHNICAL_DEBT'];
  search!: SearchTasksModel;
  searchForm = this.formBuilder.group({
    title: [''],
    type: [''],
    assignedTo: [''],
  })

  constructor(private taskService: TaskModelService,
              private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.getAllTasks();
  }

  getAllTasks() {
    this.taskService.getTasks().subscribe((tasks: TaskModel[]) => {
      this.allTasks = tasks;
    });
  }

  onSearch() {
    this.search = this.searchForm.value;
    return this.taskService.searchTasks(this.search).subscribe((tasks: TaskModel[]) => {
      this.searchResults = tasks;
    })
  }
}
