import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PersonlistComponent } from './person/personlist.component';
import { PersoneditComponent } from './person/personedit.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PersondetailsComponent } from './person/persondetails.component';

import { AppRoutingModule } from './app.routes';

import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    PersonlistComponent,
    PersoneditComponent,
    PersondetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
