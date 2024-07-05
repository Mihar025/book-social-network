import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookRoutingModule } from './book-routing.module';
import { BookCardComponent } from './components/book-card/book-card.component';
import { FormsModule } from "@angular/forms";
import { RatingComponent } from './components/rating/rating.component';
import {BookListComponent} from "./pages/book-list/book-list.component";

@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    FormsModule,
    BookCardComponent,
    RatingComponent,
    BookListComponent
  ]
})
export class BookModule { }
