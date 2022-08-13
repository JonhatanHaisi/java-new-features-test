import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MainHeaderModule, MainSidebarModule } from 'projects/components/src/public-api';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,

    MainHeaderModule,
    MainSidebarModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
