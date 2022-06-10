import {Component, OnInit} from '@angular/core';
import {AccountModelService} from "../services/AccountModel.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountModel} from "../models/AccountModel";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  allAccounts: AccountModel[] = [];
  accountToBeUpdated!: AccountModel;
  showUpdateForm = false;
  updateAccountForm!: FormGroup;
  createAccountForm = this.firmBuilder.group({
    firstName: [''], lastName: [''], email: ['']
  });

  constructor(private accountService: AccountModelService,
              private firmBuilder: FormBuilder) {
  }

  ngOnInit(): void {
    this.getAccounts();
  }

  private getAccounts() {
    return this.accountService.getAccounts().subscribe((accounts: AccountModel[]) => {
      this.allAccounts = accounts;
    });
  }

  onDelete(id: number) {
    this.accountService.deleteAccount(id).subscribe(() => {
      this.getAccounts();
    }, () => {
      alert("Cannot delete owner account");
    });
  }

  onSelect(account: AccountModel) {
    this.showUpdateForm = true;
    this.accountToBeUpdated = account;
    this.updateAccountForm = this.firmBuilder.group({
      firstName: account.firstName, lastName: account.lastName, email: account.email
    });
  }

  onUpdateAccount() {
    this.accountToBeUpdated.firstName = this.updateAccountForm.value.firstName;
    this.accountToBeUpdated.lastName = this.updateAccountForm.value.lastName;
    this.accountToBeUpdated.email = this.updateAccountForm.value.email;

    this.accountService.updateAccount(this.accountToBeUpdated).subscribe(() => {
      this.getAccounts();
    });

    this.showUpdateForm = false;
  }

  onCreateAccount() {
    this.accountService.createAccount(this.createAccountForm.value).subscribe(() => {
      this.getAccounts();
    },() => {
      alert("Email already exists");
    });
  }
}
