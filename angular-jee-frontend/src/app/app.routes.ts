import { Routes, RouterModule }  from '@angular/router';
import { NgModule } from '@angular/core';
import { PersonlistComponent } from './person/personlist.component';
import { PersondetailsComponent } from './person/persondetails.component';
import { PersoneditComponent } from './person/personedit.component';


// Route config let's you map routes to components
const routes: Routes = [
  // map '/persons' to the people list component
  {
    path: 'people',
    component: PersonlistComponent
  },
  // map '/persons/:id' to person details component
  {
    path: 'person/:id',
    component: PersondetailsComponent
  },
  {
    path: 'person/edit/:id',
    component: PersoneditComponent
  },

  // map '/' to '/persons' as our default route
  {
    path: '',
    redirectTo: '/people',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }