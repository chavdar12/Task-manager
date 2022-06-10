import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NavigationComponent} from "./navigation/navigation.component";
import {AccountComponent} from "./account/account.component";
import {BoardComponent} from "./board/board.component";
import {ProjectComponent} from "./project/project.component";
import {TaskComponent} from "./task/task.component";
import {SearchComponent} from "./search/search.component";

const routes: Routes = [{
  path: '', component: NavigationComponent
}, {
  path: 'account', component: AccountComponent
}, {
  path: 'board', component: BoardComponent
}, {
  path: 'project', component: ProjectComponent
}, {
  path: 'task', component: TaskComponent
}, {
  path: 'search', component: SearchComponent
}];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
