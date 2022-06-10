import {Component, OnInit} from '@angular/core';
import {ProjectModelService} from "../services/ProjectModel.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProjectGetModel} from "../models/ProjectGetModel";
import {AccountModel} from "../models/AccountModel";
import {AccountModelService} from "../services/AccountModel.service";
import {ProjectModel} from "../models/ProjectModel";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  allProjects: ProjectGetModel[] = [];
  projectToBeUpdated!: ProjectModel;
  allAccounts: AccountModel[] = [];
  showUpdateForm = false;
  createProjectForm = this.formBuilder.group({
    key: [''], title: [''], owner: ['']
  });
  updateProjectForm!: FormGroup;

  constructor(private projectService: ProjectModelService,
              private formBuilder: FormBuilder,
              private accountService: AccountModelService) {
  }

  ngOnInit(): void {
    this.getProjects();
    this.getAccounts();
  }

  getProjects() {
    return this.projectService.getProjects().subscribe((projects: ProjectGetModel[]) => {
      this.allProjects = projects;
    });
  }

  getAccounts() {
    return this.accountService.getAccounts().subscribe((accounts: AccountModel[]) => {
      this.allAccounts = accounts;
    });
  }

  onSelect(project: ProjectGetModel) {
    this.showUpdateForm = true;

    this.projectToBeUpdated = {
      id: project.id,
      key: project.key,
      title: project.title,
      owner: project.owner
    };

    this.updateProjectForm = this.formBuilder.group({
      key: project.key, title: project.title, owner: project.owner
    });
  }

  onDelete(id: number) {
    this.projectService.deleteProject(id).subscribe(() => {
      this.getProjects();
    }, () => {
      alert("Error");
    });
  }

  onCreateProject() {
    this.projectService.createProject(this.createProjectForm.value).subscribe(() => {
      this.getProjects();
    }, error => {
      alert('Project with this key already exists');
    })
  }

  onUpdate() {
    this.projectService.updateProject(this.projectToBeUpdated).subscribe(() => {
      this.getProjects();
    }, () => {
      alert('Project with this key already exists');
    });

    this.showUpdateForm = false;
  }
}
